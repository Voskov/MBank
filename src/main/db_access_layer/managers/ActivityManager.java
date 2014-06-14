package main.db_access_layer.managers;

import main.model.Activity;

import java.sql.SQLException;

public interface ActivityManager {
    Activity findActivity(long id) throws SQLException;
    Activity findActivity(Activity activity) throws SQLException;
}
