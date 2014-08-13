package main.client.text.based.client;

import main.db_access_layer.managers.ClientManager;
import main.db_access_layer.managers.PropertyManager;
import main.db_access_layer.managers.impl.ClientManagerImpl;
import main.db_access_layer.managers.impl.PropertyManagerImpl;
import main.exceptions.DbConnectorException;
import main.model.Client;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

public class Login {

    private static final int RETRIES = 3;
    private static boolean validate = true;

    private enum userType {
        ADMIN, CLIENT
    }

    public static void login(userType userType) throws SQLException, DbConnectorException {
        Scanner scanner = new Scanner(System.in);

        int attempt = 0;
        String username;
        String password;
        while (attempt <= RETRIES) {
            try {
                System.out.println("Please enter your username");
                username = scanner.next();
                System.out.println("Please enter your password");
                password = scanner.next();
            } catch (Exception e) {
                System.out.println("You must enter a valid input");
                attempt++;
                continue;
            }
            if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
                System.out.println("You must enter both your username and your password");
                attempt++;
                continue;
            }
            boolean authenticate = true;
            switch (userType) {
                case ADMIN:
                    authenticateAdmin(username, password);
                    break;
                case CLIENT:
                    authenticateClient(username, password);
                    break;
            }
            attempt++;
        }
    }

    private static boolean authenticateAdmin(String username, String password) throws DbConnectorException, SQLException {
        PropertyManager pm = new PropertyManagerImpl();
        HashMap adminCredentials = pm.getAdminCredentials();
        return ((adminCredentials.get("admin_username") == username) && (adminCredentials.get("admin_password") == password));
    }

    private static boolean authenticateClient(String username, String password) throws DbConnectorException, SQLException {
        ClientManager cm = new ClientManagerImpl();
        Client dbClient = cm.findClient(username);
        if (dbClient == null){

        }
        return (dbClient.getPassword().equals(password));
    }

    public static void main(String[] args) throws SQLException, DbConnectorException {
        authenticateClient("admin", "admin");
    }
}
