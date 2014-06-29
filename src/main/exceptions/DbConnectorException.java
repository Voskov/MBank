package main.exceptions;

import java.util.logging.Logger;

public class DbConnectorException extends Exception {
    public DbConnectorException(Throwable e) {

    }

    public DbConnectorException(String msg, Throwable e) {
        //TODO - Log the message
    }

    public DbConnectorException(String msg) {
        //TODO - Log the message
    }
}
