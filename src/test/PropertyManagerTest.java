package test;

import main.managers.PropertyManager;
import main.managers.impl.PropertyManagerImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PropertyManagerTest {

    @Test
    public void testGetValue(){
        PropertyManagerImpl propertyManager = new PropertyManagerImpl();
        propertyManager.connectToDb();

        String platinum_deposit_rate = propertyManager.getProperty("platinum_deposit_rate");
        System.out.println(platinum_deposit_rate);
    }

}