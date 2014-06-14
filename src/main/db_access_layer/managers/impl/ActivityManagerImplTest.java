package main.db_access_layer.managers.impl;

import main.model.Activity;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class ActivityManagerImplTest {
    ActivityManagerImpl am = null;
    Activity testActivity = new Activity(54321, 1000.0, new Date(114, 5, 23), 1.0, "Test Activity");

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
        Activity activity = am.findActivity(4);
        System.out.println(activity.toString());
    }
}