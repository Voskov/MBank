package main.services;

/**
 * Created by Einstine on 30/05/2014.
 */
public interface LoginLogoutService {

    void logClientIn(String username, String password) throws Exception;

    void logClientOut(long clientId) throws Exception;
}
