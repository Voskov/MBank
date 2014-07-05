package main.exceptions;

public class MaintenanceException extends Exception{
    public MaintenanceException() {
    }

    public MaintenanceException(String message) {
        super(message);
    }
}
