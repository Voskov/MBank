package main.managers.impl;
import main.managers.ClientManager;
import main.model.Client;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;

public class ClientManagerImpl extends DbConnector implements ClientManager {

    public ClientManagerImpl() {
        connectToDb();
    }

    public void createClient(long client_id, String client_name, String password, String type, String address, String email, String phone, String comment) {
        try {
            String sqlStatement = "INSERT INTO Clients VALUES(" + client_id + ", '" + client_name + "', '" + password + "', '" + type + "', '" + address + "', '" + email + "', '" + phone + "', '" + comment + "')";
            stmt.executeUpdate(sqlStatement);
            String msg = "Client " + client_name + " was created on DB";
            LOGGER.log(Level.INFO, msg);

        } catch (SQLIntegrityConstraintViolationException e) {
            String msg = "Client " + client_id + " already exists on DB. Client wasn't added";
            LOGGER.log(Level.WARNING, msg);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            e.printStackTrace();
        }
    }

    public long createClient(Client client) {
        try {
            String sqlStatement = "INSERT INTO Clients VALUES(" + client.getClient_id() + ", '" + client.getClient_name() + "', '" + client.getPassword() + "', '" + client.getAccountType().toString() + "', '" + client.getAddress() + "', '" + client.getEmail() + "', '" + client.getPhone() + "', '" + client.getComment() + "')";
            stmt.executeUpdate(sqlStatement);
            String msg = "Client " + client.getClient_name() + " was created on DB";
            LOGGER.log(Level.INFO, msg);

        } catch (SQLIntegrityConstraintViolationException e) {
            String msg = "Client " + client.getClient_id() + " already exists on DB. Client wasn't added";
            LOGGER.log(Level.WARNING, msg);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            e.printStackTrace();
        }

        return client.getClient_id();
    }

    @Override
    public void updateClient(long client_id, String param, String value) {
        try {
            String sqlStatement = "UPDATE Clients SET  WHERE client_id=" + client_id;
            stmt.executeUpdate(sqlStatement);
            String msg = "Client " + client_id + " was deleted from DB";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateClient(Client client, String param, String value) {
        try {
            String sqlStatement = "UPDATE Clients SET  WHERE client_id=" + client.getClient_id();
            stmt.executeUpdate(sqlStatement);
            String msg = "Client " + client.getClient_id() + " was deleted from DB";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void updateClientAddress(long client_id, String new_address) {
        try {
            String sqlStatement = "UPDATE Clients SET address=" + new_address + " WHERE client_id=" + client_id;
            stmt.executeUpdate(sqlStatement);
            String msg = "Client " + client_id + " was updated";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateClientAddress(Client client, String new_address) {
        try {
            String sqlStatement = "UPDATE Clients SET address=" + new_address + " WHERE client_id=" + client.getClient_id();
            stmt.executeUpdate(sqlStatement);
            String msg = "Client " + client.getClient_id() + " was updated";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateClientEmail(long client_id, String new_email) {
        try {
            String sqlStatement = "UPDATE Clients SET email=" + new_email + " WHERE client_id=" + client_id;
            stmt.executeUpdate(sqlStatement);
            String msg = "Client " + client_id + " was updated";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateClientEmail(Client client, String new_email) {
        try {
            String sqlStatement = "UPDATE Clients SET email=" + new_email + " WHERE client_id=" + client.getClient_id();
            stmt.executeUpdate(sqlStatement);
            String msg = "Client " + client.getClient_id() + " was updated";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateClientPhone(long client_id, String new_phone) {
        try {
            String sqlStatement = "UPDATE Clients SET phone='" + new_phone + "' WHERE client_id=" + client_id;
            stmt.executeUpdate(sqlStatement);
            String msg = "Client " + client_id + " was updated";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateClientPhone(Client client, String new_phone) {
        try {
            String sqlStatement = "UPDATE Clients SET phone='" + new_phone + "' WHERE client_id=" + client.getClient_id();
            stmt.executeUpdate(sqlStatement);
            String msg = "Client " + client.getClient_id() + " was updated";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Client findById(long id) {
        String sqlStr = "SELECT * FROM Clients WHERE client_id=" + id;
        try {
            stmt.executeQuery(sqlStr);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //TODO
        return null;
    }

    public void deleteClient(long client_id) {
        try {
            String sqlStatement = "DELETE FROM Clients WHERE client_id=" + client_id;
            stmt.executeUpdate(sqlStatement);
            String msg = "Client " + client_id + " was deleted from DB";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteClient(Client client) {
        try {
            String sqlStatement = "DELETE FROM Clients WHERE client_id=" + client.getClient_id();
            stmt.executeUpdate(sqlStatement);
            String msg = "Client " + client.getClient_id() + " was deleted from DB";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

