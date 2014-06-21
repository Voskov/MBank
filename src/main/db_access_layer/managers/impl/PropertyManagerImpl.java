package main.db_access_layer.managers.impl;

import main.AccountType;
import main.db_access_layer.managers.PropertyManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;

public class PropertyManagerImpl extends DbConnectorManagerImpl implements PropertyManager {


    public PropertyManagerImpl() {
        this.connectToDb();
    }

    // This method receives the property name as a string
    // And returns the corresponding value as a string
    @Override
    public Double getProperty(String property) throws Exception {
        String value = null;
        sqlStrBldr = new StringBuilder("SELECT prop_value FROM properties WHERE prop_key= '");
        sqlStrBldr.append(property);
        sqlStrBldr.append("'");
        try {
            ResultSet res = stmt.executeQuery(String.valueOf(sqlStrBldr));
            if (res.next()) {
                value = res.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            return Double.valueOf(value); // Yes, I COULD return a string, but the I would have to parse it everywhere
        } catch (Exception e) { //TODO
            throw new Exception("Can't fetch string using this");
        }
    }

    @Override
    public double getProperty(AccountType type, String property) throws Exception {
        String fullProperty = type.toString().toLowerCase() + "_" + property;
        Double propertyValue = this.getProperty(fullProperty);
        return propertyValue;
    }

    @Override
    public HashMap<String, String> getAdminCredentials() throws SQLException {
        sqlStrBldr = new StringBuilder("SELECT * FROM properties WHERE prop_key LIKE 'admin%'");
        HashMap adminCredentials = new HashMap();
        ResultSet res = stmt.executeQuery(sqlStrBldr.toString());
        while (res.next()) {
            adminCredentials.put(res.getString(1), res.getString(2));
        }
        return adminCredentials;
    }

    // This method receives the property name as a string
    // And the new value as a string, ans sets the value to the property
    @Override
    public void setProperty(String property, String value) {
        sqlStrBldr = new StringBuilder("UPDATE properties SET prop_value='");
        sqlStrBldr.append(value);
        sqlStrBldr.append("' WHERE prop_key='");
        sqlStrBldr.append(property).append("'");
        try {
            stmt.executeUpdate(sqlStrBldr.toString());
            LOGGER.log(Level.INFO, "Property " + property + " was updated with " + value);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Could not update property");
            e.printStackTrace();
        }
    }

    @Override
    public void setProperty(String property, double value) {
        sqlStrBldr = new StringBuilder("UPDATE properties SET prop_value=");
        sqlStrBldr.append(String.valueOf(value));
        sqlStrBldr.append("WHERE prop_key=");
        sqlStrBldr.append(property);
        try {
            stmt.executeUpdate(sqlStrBldr.toString());
            LOGGER.log(Level.INFO, "Property " + property + " was updated with " + value);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Could not update property");
            e.printStackTrace();
        }
    }
}
