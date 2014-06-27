package main.db_access_layer.managers.impl;

import main.AccountType;
import main.db_access_layer.managers.AccountManager;
import main.db_access_layer.managers.ClientManager;
import main.exceptions.DbConnectorException;
import main.model.Account;
import main.model.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;

public class ClientManagerImpl extends DbConnectorManagerImpl implements ClientManager {

    public ClientManagerImpl() throws DbConnectorException {
        connectToDb();
    }

    public void createClient(long clientId, String clientName, String password, String type, String address, String email, String phone, String comment) {
        try {
            sqlStrBldr = new StringBuilder("INSERT INTO Clients VALUES(");
            sqlStrBldr.append(clientId).append(", '");
            sqlStrBldr.append(clientName).append(", '");
            sqlStrBldr.append(password).append(", '");
            sqlStrBldr.append(type).append(", '");
            sqlStrBldr.append(address).append(", '");
            sqlStrBldr.append(email).append(", '");
            sqlStrBldr.append(phone).append(", '");
            sqlStrBldr.append(comment).append(", '");
            stmt.executeUpdate(sqlStrBldr.toString());
            String msg = "Client " + clientName + " was created on DB";
            LOGGER.log(Level.INFO, msg);

        } catch (SQLIntegrityConstraintViolationException e) {
            String msg = "Client " + clientId + " already exists on DB. Client wasn't added";
            LOGGER.log(Level.WARNING, msg);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            e.printStackTrace();
        }
    }

    @Deprecated
    @Override
    public long createClient(Client client, Account account) throws DbConnectorException {
        AccountType accountType;
        if (account.getBalance() > 100000) {
            accountType = AccountType.GOLD;
        } else if (account.getBalance() > 1000000)
            accountType = AccountType.PLATINUM;
        else
            accountType = AccountType.REGULAR;
        try {
            String sqlStatement = "INSERT INTO Clients VALUES(" + client.getClientId() + ", '" + client.getClientName() + "', '" + client.getPassword() + "', '" + accountType.toString() + "', '" + client.getAddress() + "', '" + client.getEmail() + "', '" + client.getPhone() + "', '" + client.getComment() + "')";
            stmt.executeUpdate(sqlStatement);
            String msg = "Client " + client.getClientName() + " was created on DB";
            LOGGER.log(Level.INFO, msg);
            AccountManager accountManager = new AccountManagerImpl(stmt);
            accountManager.createAccount(account);
        } catch (SQLIntegrityConstraintViolationException e) {
            String msg = "Client " + client.getClientId() + " already exists on DB. Client wasn't added";
            LOGGER.log(Level.WARNING, msg);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            e.printStackTrace();
        }

        return client.getClientId();
    }

    public void createClient(Client client) throws DbConnectorException {
        sqlStrBldr = new StringBuilder("INSERT INTO Clients ");
        sqlStrBldr.append("(client_name, password, type, address, email, phone, comment) VALUES('");
        sqlStrBldr.append(client.getClientName()).append("', '");
        sqlStrBldr.append(client.getPassword()).append("', '");
        sqlStrBldr.append(client.getAccountType().toString()).append("', '");
        sqlStrBldr.append(client.getAddress()).append("', '");
        sqlStrBldr.append(client.getEmail()).append("', '");
        sqlStrBldr.append(client.getPhone()).append("', '");
        sqlStrBldr.append(client.getComment()).append("')");
        try {
            stmt.executeUpdate(sqlStrBldr.toString());
            String msg = "Client " + client.getClientName() + " was created on DB";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLIntegrityConstraintViolationException e) {
            String msg = "Client " + client.getClientId() + " already exists on DB. Client wasn't added";
            LOGGER.log(Level.WARNING, msg);
            throw new DbConnectorException(msg, e);
        } catch (SQLException e) {
            disconnect();
            throw new DbConnectorException(e);
        }
    }

    @Override
    public void updateClient(long clientId, String param, String value) throws DbConnectorException {
        try {
            String sqlStatement = "UPDATE Clients SET  WHERE client_id=" + clientId;
            stmt.executeUpdate(sqlStatement);
            String msg = "Client " + clientId + " was deleted from DB";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLException e) {
            disconnect();
            throw new DbConnectorException(e);
        }
    }

    public void updateClient(Client client, String param, String value) throws DbConnectorException {
        try {
            sqlStrBldr = new StringBuilder("UPDATE Clients SET  WHERE client_id=").append(client.getClientId());
            stmt.executeUpdate(sqlStrBldr.toString());
            String msg = "Client " + client.getClientId() + " was deleted from DB";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLException e) {
            disconnect();
            throw new DbConnectorException(e);
        }
    }

    @Override
    public void updateClient(Client client) throws DbConnectorException {
        sqlStrBldr = new StringBuilder("UPDATE clients SET");
        sqlStrBldr.append(" client_name='").append(client.getClientName());
        sqlStrBldr.append("', password='").append(client.getPassword());
        sqlStrBldr.append("', type='").append(client.getAccountType());
        sqlStrBldr.append("', address='").append(client.getAddress());
        sqlStrBldr.append("', email='").append(client.getEmail());
        sqlStrBldr.append("', phone='").append(client.getPhone());
        sqlStrBldr.append("', comment='").append(client.getComment());
        sqlStrBldr.append("' WHERE client_id=").append(client.getClientId());
        try {
            stmt.executeUpdate(sqlStrBldr.toString());
            String msg = "Client " + client.getClientId() + " was deleted from DB";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLException e) {
            disconnect();
            throw new DbConnectorException(e);
        }
    }

    @Override
    public void updateClientAddress(long clientId, String newAddress) throws DbConnectorException {
        try {
            String sqlStatement = "UPDATE Clients SET address=" + newAddress + " WHERE client_id=" + clientId;
            stmt.executeUpdate(sqlStatement);
            String msg = "Client " + clientId + " was updated";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLException e) {
            throw new DbConnectorException(e);
        }
    }

    @Override
    public void updateClientAddress(Client client, String newAddress) throws DbConnectorException {
        updateClientAddress(client.getClientId(), newAddress);
    }

    @Override
    public void updateClientEmail(long clientId, String newEmail) throws DbConnectorException {
        try {
            String sqlStatement = "UPDATE Clients SET email=" + newEmail + " WHERE client_id=" + clientId;
            stmt.executeUpdate(sqlStatement);
            String msg = "Client " + clientId + " was updated";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLException e) {
            throw new DbConnectorException(e);
        }
    }

    @Override
    public void updateClientEmail(Client client, String newEmail) throws DbConnectorException {
        updateClientEmail(client.getClientId(), newEmail);
    }

    @Override
    public void updateClientPhone(long clientId, String newPhone) throws DbConnectorException {
        try {
            String sqlStatement = "UPDATE Clients SET phone='" + newPhone + "' WHERE client_id=" + clientId;
            stmt.executeUpdate(sqlStatement);
            String msg = "Client " + clientId + " was updated";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLException e) {
            throw new DbConnectorException(e);
        }
    }

    public void updateClientPhone(Client client, String newPhone) throws DbConnectorException {
        updateClientPhone(client.getClientId(), newPhone);
    }

    public void deleteClient(long clientId) throws DbConnectorException {
        try {
            String sqlStatement = "DELETE FROM Clients WHERE client_id=" + clientId;
            stmt.executeUpdate(sqlStatement);
            String msg = "Client " + clientId + " was deleted from DB";
            LOGGER.log(Level.INFO, msg);
        } catch (SQLException e) {
            throw new DbConnectorException(e);
        }

    }

    public void deleteClient(Client client) throws DbConnectorException {
        deleteClient(client.getClientId());
    }

    public Client creteClientFromSqlResult(String sqlQuery) throws DbConnectorException {
        try {
            ResultSet res = stmt.executeQuery(sqlQuery);
            if (res.next()) {
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
            LOGGER.log(Level.SEVERE, "Could not create Client");
            throw new DbConnectorException(e);
        }
        return null;
    }


    @Override
    public Client findClient(long clientId) throws DbConnectorException {
        sqlStrBldr = new StringBuilder("SELECT * FROM Clients WHERE client_id=").append(clientId);
        return creteClientFromSqlResult(sqlStrBldr.toString());
    }

    @Override
    public Client findClient(String clientName) throws DbConnectorException {
        sqlStrBldr = new StringBuilder("SELECT * FROM Clients WHERE client_name='").append(clientName).append("'");
        return creteClientFromSqlResult(sqlStrBldr.toString());
    }

    @Override
    public Client findClient(Client client) throws DbConnectorException {
        return findClient(client.getClientId());
    }

    @Override
    public void deleteAllClients() throws DbConnectorException {
        String sqlStr = "DELETE FROM Clients";
        String logMessage = "All clients were deleted";
        DbConnectorManagerImpl.executeStatement(sqlStr, logMessage);
    }
}