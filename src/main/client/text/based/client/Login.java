package main.client.text.based.client;

import java.util.Scanner;

public class Login {

    private static final int RETRIES = 3;
    private static boolean validate = true;

    public static void clientLogin() {
        Scanner scanner = new Scanner(System.in);

        int attempt = 0;
        String username = "";
        String password = "";
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
            if (authenticate){
                break;
            }
            attempt++;
        }
    }

    public static void main(String[] args) {
        clientLogin();
    }
}
