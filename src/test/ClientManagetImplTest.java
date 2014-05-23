package test;

import main.model.Client;
import org.testng.annotations.Test;

public class ClientManagetImplTest {


    @Test
    public void testCreateNewUser(){
        Client client = new Client(1234567, "Test Name", "pasword", "GOLD", "address 6", "test@email.com", "054-1234567", "comment");

    }
}