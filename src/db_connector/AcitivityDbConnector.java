package db_connector;

import classes.Activity;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class AcitivityDbConnector extends DbConnector {

    public AcitivityDbConnector() {
        connectToDb();
    }

    public void addActivity(Activity act){
        try {
            Statement stmt = con.createStatement();
            String statement = "INSERT INTO Activity VALUES(" + act.getId() + ", " + act.getClient_id() + ", " + act.getAmount() + ", '" + act.getActivity_date() + "', " + act.getCommission() + ", '" + act.getDescription() + "')";
            System.out.println(statement);
            stmt.executeUpdate(statement);
            String msg = "Activity" + act.getId() + " was created on DB";
            LOGGER.log(Level.INFO, msg);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
