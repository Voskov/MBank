package main.db_access_layer.managers;

import main.model.Activity;

import java.sql.SQLException;
import java.util.HashSet;

public interface ActivityManager {
    Activity findActivity(long activityId) throws SQLException;
    Activity findActivity(Activity activity) throws SQLException;

    HashSet<Activity> findAllClientActivities(long clientId) throws SQLException;
}
