package main.managers.impl;

import main.managers.ProperyManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PropertyManagerImpl extends DbConnectorManagerImpl implements ProperyManager{

    // This method receives the property name as a string
    // And returns the corresponding value as a string
    @Override
    public String getProperty(String property) {
        String value = null;
        sqlStrBldr.append("SELECT prop_value FROM properties WHERE prop_key= '");
        sqlStrBldr.append(property);
        sqlStrBldr.append("'");
        try {
            ResultSet res = stmt.executeQuery(String.valueOf(sqlStrBldr));
            if (res.next()){
                value = res.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

    // This method receives the property name as a string
    // And the new value as a string, ans sets the value to the property
    @Override
    public void setProperty(String property, String value) {

    }
}
