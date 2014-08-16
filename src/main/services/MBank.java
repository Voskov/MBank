package main.services;

import main.db_access_layer.managers.ConnectionPool;
import main.db_access_layer.managers.impl.ConnectionPoolImpl;
import main.model.Client;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MBank {

    protected static Logger LOGGER = Logger.getLogger(MBank.class.getName());
    private ConnectionPool pool;
    private Map<Long, Client> loggedInClients;

    private MBank() {
        this.pool = new ConnectionPoolImpl();
        this.loggedInClients = new HashMap<Long, Client>();
    }

    private static MBank mBank;

    public static MBank getMBank() {
        if (mBank == null) {
            mBank = new MBank();
            LOGGER.log(Level.INFO, "Creating MBank singleton");
        }
        LOGGER.log(Level.INFO, "Returning MBank singleton");
        return mBank;
    }

    public Map<Long, Client> getLoggedInClients() {
        return loggedInClients;
    }

    public boolean checkIfClientIsLoggedIn(long clientId) {
        return loggedInClients != null && !loggedInClients.isEmpty() && loggedInClients.containsKey(clientId);
    }

    public void loginClient(Client client) {
        if (loggedInClients == null) {
            loggedInClients = new HashMap<Long, Client>();
        }
        loggedInClients.put(client.getClientId(), client);
    }

    public void logoutClient(long clientId) {
        if (loggedInClients != null && !loggedInClients.isEmpty()) {
            loggedInClients.remove(clientId);
        }
    }

    public ConnectionPool getPool() {
        return pool;
    }
}