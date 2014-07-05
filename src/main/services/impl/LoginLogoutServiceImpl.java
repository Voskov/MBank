package main.services.impl;

import main.exceptions.DbConnectorException;
import main.services.MBank;
import main.db_access_layer.managers.ClientManager;
import main.db_access_layer.managers.impl.ClientManagerImpl;
import main.model.Client;
import main.services.LoginLogoutService;

import javax.naming.AuthenticationException;

public class LoginLogoutServiceImpl implements LoginLogoutService {

    @Override
    public void logClientIn(String username, String password) throws AuthenticationException, DbConnectorException {
        ClientManager cm = new ClientManagerImpl();
        Client client = cm.findClient(username);

        if (client == null) {
            throw new AuthenticationException("Client with this username does not exists");
        }

        if (!client.getPassword().equals(password)) {
            throw new AuthenticationException("Wrong password");
        }

        MBank mBank = MBank.getMBank();
        if (mBank.checkIfClientIsLoggedIn(client.getClientId())) {
            throw new AuthenticationException("Client already logged in");
        }
        mBank.loginClient(client);

    }

    @Override
    public void logClientOut(long clientId) throws AuthenticationException {
        MBank mBank = MBank.getMBank();
        if (!mBank.checkIfClientIsLoggedIn(clientId)) {
            throw new AuthenticationException("Client is not logged in");
        }
        mBank.logoutClient(clientId);
    }
}
