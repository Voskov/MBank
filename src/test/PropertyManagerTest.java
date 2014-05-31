package test;

import main.db_access_layer.managers.impl.PropertyManagerImpl;
import org.junit.Test;

public class PropertyManagerTest {

    @Test
    public void testGetValue(){
        PropertyManagerImpl propertyManager = new PropertyManagerImpl();
        propertyManager.connectToDb();

        String platinum_deposit_rate = propertyManager.getProperty("platinum_deposit_rate");
        System.out.println(platinum_deposit_rate);
    }

}