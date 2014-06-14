package main.db_access_layer.managers.impl;

import main.model.Activity;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class ActivityManagerImplTest {
    ActivityManagerImpl am = null;
    Activity testActivity = new Activity(12345, 54321, 1000.0, new Date(1400000000), 1.0, "Test Activity");

    @Before
    public void setUp() throws Exception {
        am = new ActivityManagerImpl();
    }

    @Test
    public void testAddActivity() throws Exception {
        am.addActivity(testActivity);
    }

    @Test
    public void testFindActivity() throws Exception {

    }
}