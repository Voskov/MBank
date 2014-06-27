package main.db_access_layer.managers;

import main.exceptions.DbConnectorException;
import main.model.Activity;

import java.sql.SQLException;
import java.util.HashSet;

public interface ActivityManager {
    void addActivity(Activity act) throws DbConnectorException;

    Activity findActivity(long activityId) throws SQLException, DbConnectorException;
    Activity findActivity(Activity activity) throws SQLException, DbConnectorException;

    HashSet<Activity> findAllClientActivities(long clientId) throws SQLException;
}
