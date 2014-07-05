package test;

import init.InitiateDB;
import main.db_access_layer.managers.impl.ActivityManagerImpl;
import main.model.Activity;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ActivityManagerImplTest {
    ActivityManagerImpl am = null;
    Activity testActivity = new Activity(54321, 1000.0, new Date(114, 5, 23), 1.0, "Test Activity");

    @Before
    public void setUp()  {
        am = new ActivityManagerImpl();
        InitiateDB.restartDb();
    }

    @Test
    public void testAddActivity()  {
        am.addActivity(testActivity);
    }

    @Test
    public void testFindActivityById()  {
        am.addActivity(testActivity);
        Activity activity = am.findActivity(1);
        testActivity.setActivityId(1);
        assertEquals(testActivity, activity);
    }

    @Test
    public void testFindActivityByActivity()  {
        am.addActivity(testActivity);
        testActivity.setActivityId(1);
        Activity activity = am.findActivity(testActivity);
        assertEquals(testActivity, activity);
    }
}