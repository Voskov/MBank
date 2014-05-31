package main.db_access_layer.managers;

public interface PropertyManager {
    String getProperty(String property);
    void setProperty(String property, String value);
}
