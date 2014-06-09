package main.db_access_layer.managers;

public interface PropertyManager {
    Double getProperty(String property) throws Exception;
    void setProperty(String property, String value);
}
