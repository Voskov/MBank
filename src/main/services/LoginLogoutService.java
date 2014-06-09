package main.services;

public interface LoginLogoutService {

    void logClientIn(String username, String password) throws Exception;

    void logClientOut(long clientId) throws Exception;
}
