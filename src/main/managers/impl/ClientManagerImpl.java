package main.managers.impl;
import main.AccountType;
import main.managers.AccountManager;
import main.managers.ClientManager;
import main.model.Account;
import main.model.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;

public class ClientManagerImpl extends DbConnectorManagerImpl implements ClientManager {

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

    @Override
    public long createClient(Client client, Account account) {
        AccountType accountType;
        if (account.getBalance() > 100000){
            accountType = AccountType.GOLD;
        }else if (account.getBalance() > 1000000)
            accountType = AccountType.PLATINUM;
        else
            accountType = AccountType.REGULAR;
        try {
            String sqlStatement = "INSERT INTO Clients VALUES(" + client.getClient_id() + ", '" + client.getClient_name() + "', '" + client.getPassword() + "', '" + accountType.toString() + "', '" + client.getAddress() + "', '" + client.getEmail() + "', '" + client.getPhone() + "', '" + client.getComment() + "')";
            stmt.executeUpdate(sqlStatement);
            String msg = "Client " + client.getClient_name() + " was created on DB";
            LOGGER.log(Level.INFO, msg);
            AccountManager accountManager = new AccountManagerImpl(stmt);
            accountManager.createAccount(account);
        } catch (SQLIntegrityConstraintViolationException e) {
            String msg = "Client " + client.getClient_id() + " already exists on DB. Client wasn't added";
            LOGGER.log(Level.WARNING, msg);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            e.printStackTrace();
        }

        return client.getClient_id();
    }

    public void createClient(Client client){
        sqlStrBldr.append("INSERT INTO Clients VALUES(");
        sqlStrBldr.append(client.getClient_id()).append(", '");
        sqlStrBldr.append(client.getClient_name()).append("', '");
        sqlStrBldr.append(client.getPassword()).append("', '");
        sqlStrBldr.append(client.getAccountType().toString()).append("', '");
        sqlStrBldr.append(client.getAddress()).append("', '");
        sqlStrBldr.append(client.getEmail()).append("', '");
        sqlStrBldr.append(client.getPhone()).append("', '");
        sqlStrBldr.append(client.getComment()).append("')");
        try {
            stmt.executeUpdate(sqlStrBldr.toString());
            String msg = "Client " + client.getClient_name() + " was created on DB";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLIntegrityConstraintViolationException e) {
            String msg = "Client " + client.getClient_id() + " already exists on DB. Client wasn't added";
            LOGGER.log(Level.WARNING, msg);
        } catch (SQLException e) {
            disconnect();
            e.printStackTrace();
        }
    }

    @Override
    public void updateClient(long client_id, String param, String value) {
        try {
            String sqlStatement = "UPDATE Clients SET  WHERE client_id=" + client_id;
            stmt.executeUpdate(sqlStatement);
            String msg = "Client " + client_id + " was deleted from DB";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLException e) {
            disconnect();
            e.printStackTrace();
        }
    }

    public void updateClient(Client client, String param, String value) {
        try {
            sqlStrBldr.append("UPDATE Clients SET  WHERE client_id=").append(client.getClient_id());
//            String sqlStatement = "UPDATE Clients SET  WHERE client_id=" + client.getClient_id();
            stmt.executeUpdate(sqlStrBldr.toString());
            String msg = "Client " + client.getClient_id() + " was deleted from DB";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLException e) {
            disconnect();
            e.printStackTrace();
        }
    }

    @Override
    public void updateClient(Client client){
        sqlStrBldr = new StringBuilder("UPDATE clients SET");
        sqlStrBldr.append(" client_name='").append(client.getClient_name());
        sqlStrBldr.append("', password='").append(client.getPassword());
        sqlStrBldr.append("', type='").append(client.getAccountType());
        sqlStrBldr.append("', address='").append(client.getAddress());
        sqlStrBldr.append("', email='").append(client.getEmail());
        sqlStrBldr.append("', phone='").append(client.getPhone());
        sqlStrBldr.append("', comment='").append(client.getComment());
        sqlStrBldr.append("' WHERE client_id=").append(client.getClient_id());
        try {
            stmt.executeUpdate(sqlStrBldr.toString());
            String msg = "Client " + client.getClient_id() + " was deleted from DB";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLException e) {
            disconnect();
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

    @Override
    public Client findById(long client_id) {
        String sqlStr = "SELECT * FROM Clients WHERE client_id=" + client_id;
        try {
            ResultSet res = stmt.executeQuery(sqlStr);
            if (res.next()){
                long id = res.getLong(1);
                String name = res.getString(2);
                String password = res.getString(3);
                String typeString = res.getString(4);
                AccountType accountType = AccountType.valueOf(typeString);
                String address = res.getString(5);
                String email = res.getString(6);
                String phone = res.getString(7);
                String comment = res.getString(8);
                return new Client(id, name, password, accountType, address, email, phone, comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteAllClients(){
        String sqlStr = "DELETE FROM Clients";
        String logMessage = "All clients were deleted";
        DbConnectorManagerImpl.executeStatement(sqlStr, logMessage);
    }
}

