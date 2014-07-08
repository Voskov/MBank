package main.exceptions;

public class ClientActionException extends Throwable {
    public ClientActionException(String msg, Throwable e) {
    }

    public ClientActionException(Throwable e) {
    }

    public ClientActionException(String msg) {
    }
}
