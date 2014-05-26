package test;

import junit.framework.Assert;
import main.managers.impl.ClientManagerImpl;
import main.model.Client;
import org.junit.After;
import org.testng.annotations.Test;

public class ClientManagetImplTest {

    @After
    public void after(){
        ClientManagerImpl clientManager = new ClientManagerImpl();
        cleanClientTable();
        clientManager.disconnect();
    }

    @Test
    public void testCreateNewUser(){
        Client client = populateClient();

        ClientManagerImpl clientManager = new ClientManagerImpl();
        Client new_client = clientManager.findById(client.getClient_id());
        Assert.assertNotNull(new_client);
        Assert.assertEquals(client.getClient_name(), new_client.getClient_name());
        Assert.assertEquals(client.getEmail(), new_client.getEmail());

        cleanClientTable();
        clientManager.disconnect();
    }

    private Client populateClient() {
        cleanClientTable();
        ClientManagerImpl clientManager = new ClientManagerImpl();
        Client client = new Client(1234567, "Test Name", "pasword", "GOLD", "address 6", "test@email.com", "054-1234567", "comment");
        clientManager.createClient(client);
        return client;
    }

    private void cleanClientTable(){
        ClientManagerImpl clientManager = new ClientManagerImpl();
        clientManager.deleteAllClients();
        clientManager.disconnect();
    }

   @Test
    public void testSameClient(){
       Client client = populateClient();
       ClientManagerImpl clientManager = new ClientManagerImpl();
       clientManager.createClient(client);
       clientManager.createClient(client);
       clientManager.disconnect();
   }
}