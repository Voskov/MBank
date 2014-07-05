package main.exceptions;

/**
 * Created by Ariel Voskov on 6/26/2014.
 */
public class AccountException extends Exception{
    public AccountException(String message) {
        super(message);
    }

    public AccountException() {
    }
}
