package main.db_access_layer.managers.impl;

import main.db_access_layer.managers.PropertyManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PropertyManagerImpl extends DbConnectorManagerImpl implements PropertyManager {


    public PropertyManagerImpl() {
        this.connectToDb();
    }

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
        sqlStrBldr.append("UPDATE properties SET prop_value=");
        sqlStrBldr.append(value);
        sqlStrBldr.append("WHERE prop_key=");
        sqlStrBldr.append(property);
    }

    public static void main(String[] args) {
        PropertyManagerImpl pm = new PropertyManagerImpl();
        System.out.println(pm.getProperty("regular_deposit_rate"));
    }
}
