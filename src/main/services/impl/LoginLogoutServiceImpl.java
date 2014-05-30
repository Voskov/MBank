package main.services.impl;

import main.MBank;
import main.managers.ClientManager;
import main.managers.impl.ClientManagerImpl;
import main.model.Client;
import main.services.LoginLogoutService;

public class LoginLogoutServiceImpl implements LoginLogoutService {

    @Override
    public void logClientIn(String username, String password) throws Exception {
       ClientManager cm =  new ClientManagerImpl();
        Client client = cm.findClientByClientName(username);

        if (client== null){
            throw new Exception("Client with this username does not exists");
        }

        if (!client.getPassword().equals(password)){
            throw new Exception("Wrong password");
        }

        MBank mBank = MBank.getMBank();
        if (mBank.checkIfClientIsLoggedIn(client.getClient_id())){
            throw new Exception("Client already logged in");
        }
        mBank.addLoggedInClient(client);

    }

    @Override
    public void logClientOut(long clientId) throws Exception {
        MBank mBank = MBank.getMBank();
        if (!mBank.checkIfClientIsLoggedIn(clientId)){
            throw new Exception("Client is not logged in");
        }
        mBank.logoutClient(clientId);
    }
}
