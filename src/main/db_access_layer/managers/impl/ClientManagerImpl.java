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

    public void createClient(long clientId, String clientName, String password, String type, String address, String email, String phone, String comment) throws DbConnectorException {
        sqlStrBldr = new StringBuilder("INSERT INTO Clients VALUES(");
        sqlStrBldr.append(clientId).append(", '");
        sqlStrBldr.append(clientName).append(", '");
        sqlStrBldr.append(password).append(", '");
        sqlStrBldr.append(type).append(", '");
        sqlStrBldr.append(address).append(", '");
        sqlStrBldr.append(email).append(", '");
        sqlStrBldr.append(phone).append(", '");
        sqlStrBldr.append(comment).append(", '");
        executeUpdate(sqlStrBldr);
        String msg = "Client " + clientName + " was created on DB";
        LOGGER.log(Level.INFO, msg);
    }

    @Deprecated
    @Override
    public long createClient(Client client, Account account) throws DbConnectorException {
        AccountType accountType;
        if (account.getBalance() > 100000) {            //TODO - take care of this
            accountType = AccountType.GOLD;
        } else if (account.getBalance() > 1000000)
            accountType = AccountType.PLATINUM;
        else
            accountType = AccountType.REGULAR;
        sqlStrBldr = new StringBuilder("INSERT INTO Clients VALUES(");
        sqlStrBldr.append(client.getClientId()).append(", '");
        sqlStrBldr.append(client.getClientName()).append("', '");
        sqlStrBldr.append(client.getPassword()).append("', '");
        sqlStrBldr.append(client.getAccountType().toString()).append("', '");
        sqlStrBldr.append(client.getAddress()).append("', '");
        sqlStrBldr.append(client.getEmail()).append("', '");
        sqlStrBldr.append(client.getPhone()).append("', '");
        sqlStrBldr.append(client.getComment()).append("')");

//            String sqlStatement = "INSERT INTO Clients VALUES(" + client.getClientId() + ", '" + client.getClientName() + "', '" + client.getPassword() + "', '" + accountType.toString() + "', '" + client.getAddress() + "', '" + client.getEmail() + "', '" + client.getPhone() + "', '" + client.getComment() + "')";
        executeUpdate(sqlStrBldr);
        String msg = "Client " + client.getClientName() + " was created on DB";
        LOGGER.log(Level.INFO, msg);
        AccountManager accountManager = new AccountManagerImpl();
        accountManager.createAccount(account);
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
        executeUpdate(sqlStrBldr);
        String msg = "Client " + client.getClientName() + " was created on DB";
        LOGGER.log(Level.INFO, msg);
    }

    @Override
    public void updateClient(long clientId, String param, String value) throws DbConnectorException {
        sqlStrBldr = new StringBuilder("UPDATE Clients SET  WHERE client_id=").append(clientId);
        executeUpdate(sqlStrBldr);
        String msg = "Client " + clientId + " was deleted from DB";
        LOGGER.log(Level.INFO, msg);
    }

    public void updateClient(Client client, String param, String value) throws DbConnectorException {
        sqlStrBldr = new StringBuilder("UPDATE Clients SET  WHERE client_id=").append(client.getClientId());
        executeUpdate(sqlStrBldr);
        String msg = "Client " + client.getClientId() + " was deleted from DB";
        LOGGER.log(Level.INFO, msg);
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
        executeUpdate(sqlStrBldr);
        String msg = "Client " + client.getClientId() + " was deleted from DB";
        LOGGER.log(Level.INFO, msg);
    }

    @Override
    public void updateClientAddress(long clientId, String newAddress) throws DbConnectorException {
        sqlStrBldr = new StringBuilder("UPDATE Clients SET address=").append(newAddress).append(" WHERE client_id=").append(clientId);
        executeUpdate(sqlStrBldr);
        String msg = "Client " + clientId + " was updated";
        LOGGER.log(Level.INFO, msg);
    }

    @Override
    public void updateClientAddress(Client client, String newAddress) throws DbConnectorException {
        updateClientAddress(client.getClientId(), newAddress);
    }

    @Override
    public void updateClientEmail(long clientId, String newEmail) throws DbConnectorException {
        String sqlStatement = "UPDATE Clients SET email=" + newEmail + " WHERE client_id=" + clientId;
        executeUpdate(sqlStatement);
        String msg = "Client " + clientId + " was updated";
        LOGGER.log(Level.INFO, msg);
    }

    @Override
    public void updateClientEmail(Client client, String newEmail) throws DbConnectorException {
        updateClientEmail(client.getClientId(), newEmail);
    }

    @Override
    public void updateClientPhone(long clientId, String newPhone) throws DbConnectorException {
        String sqlStatement = "UPDATE Clients SET phone='" + newPhone + "' WHERE client_id=" + clientId;
        executeUpdate(sqlStatement);
        String msg = "Client " + clientId + " was updated";
        LOGGER.log(Level.INFO, msg);
    }

    public void updateClientPhone(Client client, String newPhone) throws DbConnectorException {
        updateClientPhone(client.getClientId(), newPhone);
    }

    public void deleteClient(long clientId) throws DbConnectorException {
        sqlStrBldr = new StringBuilder("DELETE FROM Clients WHERE client_id=").append(clientId);
        executeUpdate(sqlStrBldr);
        String msg = "Client " + clientId + " was deleted from DB";
        LOGGER.log(Level.INFO, msg);
    }

    public void deleteClient(Client client) throws DbConnectorException {
        deleteClient(client.getClientId());
    }

    public Client creteClientFromSqlResult(ResultSet res) throws DbConnectorException {
        try {
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
            LOGGER.log(Level.WARNING, "Could not create Client");
            throw new DbConnectorException(e);
        }
        return null;
    }


    @Override
    public Client findClient(long clientId) throws DbConnectorException {
        sqlStrBldr = new StringBuilder("SELECT * FROM Clients WHERE client_id=").append(clientId);
        ResultSet res = executeQuery(sqlStrBldr.toString());
        return creteClientFromSqlResult(res);
    }

    @Override
    public Client findClient(String clientName) throws DbConnectorException {
        sqlStrBldr = new StringBuilder("SELECT * FROM Clients WHERE client_name='").append(clientName).append("'");
        ResultSet res = executeQuery(sqlStrBldr.toString());
        return creteClientFromSqlResult(res);
    }

    @Override
    public Client findClient(Client client) throws DbConnectorException {
        return findClient(client.getClientId());
    }

    @Override
    public void deleteAllClients() throws DbConnectorException {
        String sqlStr = "DELETE FROM Clients";
        executeUpdate(sqlStr);
        String logMessage = "All clients were deleted";
        LOGGER.log(Level.INFO, logMessage);
    }
}