package main.db_access_layer.managers;

import main.AccountType;
import main.exceptions.DbConnectorException;

import java.sql.SQLException;
import java.util.HashMap;

public interface PropertyManager {
    Double getProperty(String property) throws DbConnectorException;
    double getProperty(AccountType type, String property) throws DbConnectorException;

    HashMap<String, String> getAdminCredentials() throws SQLException;

    void setProperty(String property, String value);
    void setProperty(String property, double value);
}
