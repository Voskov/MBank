package main.services;

import main.model.Client;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MBank {

    protected static Logger LOGGER = Logger.getLogger(MBank.class.getName());

     private Map<Long, Client> loggedInClients;

    private static MBank mBank;

    public static MBank getMBank(){
        if (mBank == null){
            mBank = new MBank();
            LOGGER.log(Level.INFO, "Creating MBank singleton");
        }
        LOGGER.log(Level.INFO, "Returning MBank singleton");
        return mBank;
    }

    public Map<Long, Client> getLoggedInClients() {
        return loggedInClients;
    }

    public boolean checkIfClientIsLoggedIn(long clientId){
        if (loggedInClients!=null && ! loggedInClients.isEmpty() && loggedInClients.containsKey(clientId) ) {
            return true;
        }
        return false;
    }

    public void addLoggedInClient(Client client) {
        if (loggedInClients== null){
            loggedInClients = new HashMap<Long, Client>();
        }
        loggedInClients.put(client.getClient_id(), client);
    }

    public void logoutClient(long clientId) {
        if (loggedInClients!= null && !loggedInClients.isEmpty()){
            loggedInClients.remove(clientId);
        }
    }
}
