package db_connector;

import classes.Client;
import com.sun.media.jfxmedia.logging.Logger;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.logging.Level;

public class ClientDbConnector extends DbConnector {

    public ClientDbConnector() {
        connectToDb();
    }

    public void addClient(Client client) {

        try {
            Statement stmt = con.createStatement();
            String sqlStatement = "INSERT INTO Clients VALUES(" + client.getClient_id() + ", '" + client.getClient_name() + "', '" + client.getPassword() + "', '" + client.getAccountType() + "', '" + client.getAddress() + "', '" + client.getEmail() + "', '" + client.getPhone() + "', '" + client.getComment() + "')";
            stmt.executeUpdate(sqlStatement);
            String msg = "Client " + client.getClient_name() + " was created on DB";
            LOGGER.log(Level.INFO, msg);

        } catch (SQLIntegrityConstraintViolationException e) {
            String msg = "Client " + client.getClient_name() + "already exists on DB. Client wasn't added";
            LOGGER.log(Level.WARNING ,msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
