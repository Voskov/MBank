package main.db_access_layer.managers;

import main.AccountType;

import java.sql.SQLException;
import java.util.HashMap;

public interface PropertyManager {
    Double getProperty(String property) throws Exception;
    double getProperty(AccountType type, String property) throws Exception;

    HashMap<String, String> getAdminCredentials() throws SQLException;

    void setProperty(String property, String value);
    void setProperty(String property, double value);
}
