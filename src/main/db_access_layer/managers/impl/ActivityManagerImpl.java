package main.db_access_layer.managers.impl;

import main.db_access_layer.managers.ActivityManager;
import main.model.Activity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;

public class ActivityManagerImpl extends DbConnectorManagerImpl implements ActivityManager {

    public ActivityManagerImpl() {
        connectToDb();
    }

    public void addActivity(Activity act) {
        try {
            sqlStrBldr = new StringBuilder("INSERT INTO Activity ");
            sqlStrBldr.append("(client_id, amount, activity_date, commission, description) ");
            sqlStrBldr.append("VALUES (");
            sqlStrBldr.append(act.getClientId()).append(", ");
            sqlStrBldr.append(act.getAmount()).append(", '");
            sqlStrBldr.append(act.getActivityDate()).append("', ");
            sqlStrBldr.append(act.getCommission()).append(", '");
            sqlStrBldr.append(act.getDescription()).append("')");
            stmt.executeUpdate(sqlStrBldr.toString());
            String msg = "Activity was created on DB";
            LOGGER.log(Level.INFO, msg);

        } catch (SQLIntegrityConstraintViolationException e) {
            String msg = "An activity with the same id already exists on DB. Activity wasn't added";
            LOGGER.log(Level.WARNING, msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addActivity(long id, long clientId, double amount, String date, double commission, String description) {
        try {

            String statement = "INSERT INTO Activity VALUES(" + id + ", " + clientId + ", " + amount + ", '" + date + "', " + commission + ", '" + description + "')";
            System.out.println(statement);
            stmt.executeUpdate(statement);
            String msg = "Activity" + id + " was created on DB";
            LOGGER.log(Level.INFO, msg);

        } catch (SQLIntegrityConstraintViolationException e) {
            String msg = "Activity " + id + " already exists on DB. Activity wasn't added";
            LOGGER.log(Level.WARNING, msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Activity findActivity(long activityId) throws SQLException {
        sqlStrBldr = new StringBuilder("SELECT * FROM Activity WHERE activity_id=").append(activityId);
        Activity dbActivity = null;
        ResultSet res = stmt.executeQuery(sqlStrBldr.toString());
        if (res.next()) {
            dbActivity = buildActivity(res);
        }
        return dbActivity;
    }

    @Override
    public Activity findActivity(Activity activity) throws SQLException {
        return findActivity(activity.getActivityId());
    }

    private Activity buildActivity(ResultSet res) throws SQLException {
        return new Activity(res.getLong(1), res.getLong(2), res.getDouble(3), res.getDate(4), res.getDouble(5), res.getString(6));
    }
}
