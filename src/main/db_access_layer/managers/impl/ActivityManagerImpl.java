package main.db_access_layer.managers.impl;

import main.db_access_layer.managers.ActivityManager;
import main.exceptions.DbConnectorException;
import main.model.Activity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.logging.Level;

public class ActivityManagerImpl extends DbConnectorManagerImpl implements ActivityManager {

    public ActivityManagerImpl() throws DbConnectorException {
        connectToDb();
    }

    @Override
    public void addActivity(Activity act) throws DbConnectorException {
        sqlStrBldr = new StringBuilder("INSERT INTO Activity ");
        sqlStrBldr.append("(client_id, amount, activity_date, commission, description) ");
        sqlStrBldr.append("VALUES (");
        sqlStrBldr.append(act.getClientId()).append(", ");
        sqlStrBldr.append(act.getAmount()).append(", '");
        sqlStrBldr.append(act.getActivityDate()).append("', ");
        sqlStrBldr.append(act.getCommission()).append(", '");
        sqlStrBldr.append(act.getDescription()).append("')");
        executeUpdate(sqlStrBldr);
        String msg = "Activity was created on DB";
        LOGGER.log(Level.INFO, msg);
    }

    public void addActivity(long id, long clientId, double amount, String date, double commission, String description) throws SQLException, DbConnectorException {
        String statement = "INSERT INTO Activity VALUES(" + id + ", " + clientId + ", " + amount + ", '" + date + "', " + commission + ", '" + description + "')";
        System.out.println(statement);
        executeUpdate(statement);
        String msg = "Activity" + id + " was created on DB";
        LOGGER.log(Level.INFO, msg);
    }

    @Override
    public Activity findActivity(long activityId) throws DbConnectorException {
        sqlStrBldr = new StringBuilder("SELECT * FROM Activity WHERE activity_id=").append(activityId);
        Activity dbActivity = null;
        ResultSet res = null;
        try {
            res = executeQuery(sqlStrBldr);
            if (res.next()) {
                dbActivity = buildActivity(res);
            }
        } catch (SQLException e) {
            throw new DbConnectorException(e);
        }
        return dbActivity;
    }

    @Override
    public Activity findActivity(Activity activity) throws DbConnectorException {
        return findActivity(activity.getActivityId());
    }

    private Activity buildActivity(ResultSet res) throws SQLException {
        return new Activity(res.getLong(1), res.getLong(2), res.getDouble(3), res.getDate(4), res.getDouble(5), res.getString(6));

    }

    @Override
    public HashSet<Activity> findAllClientActivities(long clientId) throws SQLException {
        sqlStrBldr = new StringBuilder("SELECT * FROM Activity WHERE client_id=").append(clientId);
        HashSet<Activity> allActivities = new HashSet<Activity>();
        ResultSet res = stmt.executeQuery(sqlStrBldr.toString());
        if (res.next()) {
            allActivities.add(buildActivity(res));
        }
        return allActivities;
    }
}
