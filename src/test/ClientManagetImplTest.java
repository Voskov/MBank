package test;

import junit.framework.Assert;
import main.AccountType;
import main.managers.impl.ClientManagerImpl;
import main.model.Client;
import org.testng.annotations.Test;

public class ClientManagetImplTest {

    @Test
    public void testCreateNewUser(){
        Client client = populateClient();

        ClientManagerImpl clientManager = new ClientManagerImpl();
        Client new_client = clientManager.findClientById(client.getClient_id());
        Assert.assertNotNull(new_client);
        Assert.assertEquals(client.getClient_name(), new_client.getClient_name());
        Assert.assertEquals(client.getEmail(), new_client.getEmail());

        cleanClientTable();
    }

    private Client populateClient() {
        cleanClientTable();
        ClientManagerImpl clientManager = new ClientManagerImpl();
        Client client = new Client(1234567, "Test Name", "pasword", AccountType.GOLD, "address 6", "test@email.com", "054-1234567", "comment");
        clientManager.createClient(client);
        return client;
    }

    private void cleanClientTable(){
        ClientManagerImpl clientManager = new ClientManagerImpl();
        clientManager.deleteAllClients();
    }

   @Test
    public void testSameClient(){
       Client client = populateClient();
       ClientManagerImpl clientManager = new ClientManagerImpl();
       clientManager.createClient(client);
       clientManager.createClient(client);
   }
}