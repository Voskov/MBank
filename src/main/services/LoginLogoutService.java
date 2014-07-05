package main.services;

import main.exceptions.DbConnectorException;

import javax.naming.AuthenticationException;

public interface LoginLogoutService {

    void logClientIn(String username, String password) throws AuthenticationException, DbConnectorException;

    void logClientOut(long clientId) throws AuthenticationException;
}
