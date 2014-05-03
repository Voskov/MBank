package db_connector;

import classes.Activity;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.logging.Level;

public class AcitivityDbConnector extends DbConnector {

    public AcitivityDbConnector() {
        connectToDb();
    }

    public void addActivity(Activity act) {
        try {
            Statement stmt = con.createStatement();
            String statement = "INSERT INTO Activity VALUES(" + act.getId() + ", " + act.getClient_id() + ", " + act.getAmount() + ", '" + act.getActivity_date() + "', " + act.getCommission() + ", '" + act.getDescription() + "')";
            System.out.println(statement);
            stmt.executeUpdate(statement);
            String msg = "Activity" + act.getId() + " was created on DB";
            LOGGER.log(Level.INFO, msg);

        } catch (SQLIntegrityConstraintViolationException e) {
            String msg = "Account" + act.getId() + " already exists on DB. Client wasn't added";
            LOGGER.log(Level.WARNING, msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addActivity(long id, long client_id, double amount, String date, double commission, String description) {
        try {
            Statement stmt = con.createStatement();
            String statement = "INSERT INTO Activity VALUES(" + id + ", " + client_id + ", " + amount + ", '" + date + "', " + commission + ", '" + description + "')";
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

}
