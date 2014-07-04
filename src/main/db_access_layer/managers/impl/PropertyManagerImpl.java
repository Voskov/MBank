package main.db_access_layer.managers.impl;

import main.AccountType;
import main.db_access_layer.managers.PropertyManager;
import main.exceptions.DbConnectorException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;

public class PropertyManagerImpl extends DbConnectorManagerImpl implements PropertyManager {


    public PropertyManagerImpl() throws DbConnectorException {
        connectToDb();
    }

    // This method receives the property name as a string
    // And returns the corresponding value as a string
    @Override
    public Double getProperty(String property) throws DbConnectorException {
        String value = null;
        sqlStrBldr = new StringBuilder("SELECT prop_value FROM properties WHERE prop_key='");
        sqlStrBldr.append(property).append("'");
        try {
            ResultSet res = executeQuery(sqlStrBldr);
            if (res.next()) {
                value = res.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            return Double.valueOf(value); // Yes, I COULD return a string, but then I would have to parse it everywhere
        } catch (Exception e) {
            throw new DbConnectorException("Can't fetch string using this", e);
        }
    }

    @Override
    public double getProperty(AccountType type, String property) throws DbConnectorException {
        String fullProperty = type.toString().toLowerCase() + "_" + property;
        Double propertyValue = this.getProperty(fullProperty);
        return propertyValue;
    }

    @Override
    public HashMap<String, String> getAdminCredentials() throws SQLException, DbConnectorException {
        sqlStrBldr = new StringBuilder("SELECT * FROM properties WHERE prop_key LIKE 'admin%'");
        HashMap adminCredentials = new HashMap();
        ResultSet res = executeQuery(sqlStrBldr);
        while (res.next()) {
            adminCredentials.put(res.getString(1), res.getString(2));
        }
        return adminCredentials;
    }

    // This method receives the property name as a string
    // And the new value as a string, ans sets the value to the property
    @Override
    public void setProperty(String property, String value) throws DbConnectorException {
        sqlStrBldr = new StringBuilder("UPDATE properties SET prop_value='");
        sqlStrBldr.append(value);
        sqlStrBldr.append("' WHERE prop_key='");
        sqlStrBldr.append(property).append("'");
        executeUpdate(sqlStrBldr);
        LOGGER.log(Level.INFO, "Property " + property + " was updated with " + value);
    }

    @Override
    public void setProperty(String property, double value) throws DbConnectorException {
        sqlStrBldr = new StringBuilder("UPDATE properties SET prop_value=");
        sqlStrBldr.append(String.valueOf(value));
        sqlStrBldr.append("WHERE prop_key=");
        sqlStrBldr.append(property);
        executeUpdate(sqlStrBldr);
        LOGGER.log(Level.INFO, "Property " + property + " was updated with " + value);
    }
}
