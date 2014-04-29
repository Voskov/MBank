package db_connector;

import classes.Client;
import config.ImportDbSettings;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class ClientDbConnector extends DbConnector{

    public void addClient(Client client) {

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO Clients VALUES(" + client.getClient_id() + ", '" + client.getClient_name() + "', '" + client.getPassword() + "', '" + client.getAccountType() + "', '" + client.getAddress() + "', '" + client.getEmail() + "', '" + client.getPhone() + "', '" + client.getComment() + "')");
            String msg = "Client " + client.getClient_name() + " was created on DB";
            LOGGER.log(Level.INFO, msg);

//            con.close();                                                    // always remember to close the connection at the end
//            System.out.println("Connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ClientDbConnector(){
        connectToDb();
    }

}
