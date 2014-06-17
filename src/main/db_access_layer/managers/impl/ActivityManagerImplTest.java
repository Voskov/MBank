package main.db_access_layer.managers.impl;

import init.InitiateDB;
import junit.framework.Assert;
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
        InitiateDB.restartDb();
    }

    @Test
    public void testAddActivity() throws Exception {
        am.addActivity(testActivity);
    }

    @Test
    public void testFindActivityById() throws Exception {
        am.addActivity(testActivity);
        Activity activity = am.findActivity(1);
        testActivity.setActivityId(1);
        Assert.assertEquals(testActivity, activity);
    }

    @Test
    public void testFindActivityByActivity() throws Exception {
        am.addActivity(testActivity);
        testActivity.setActivityId(1);
        Activity activity = am.findActivity(testActivity);
        Assert.assertEquals(testActivity, activity);
    }
}