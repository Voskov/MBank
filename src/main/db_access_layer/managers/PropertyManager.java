package main.db_access_layer.managers;

import main.AccountType;

public interface PropertyManager {
    Double getProperty(String property) throws Exception;
    double getProperty(AccountType type, String property) throws Exception;

    void setProperty(String property, String value);
    void setProperty(String property, double value);
}
