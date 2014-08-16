package main.client.text.based.client;

import main.exceptions.DbConnectorException;
import main.services.MBank;

import java.sql.SQLException;

import static main.client.text.based.client.Input.multipleChoiceInput;

public class Entry {
    private static String[] INPUT_OPTIONS = {"1", "2"};
    MBank mBank = MBank.getMBank();

    public static void main(String[] args) throws SQLException, DbConnectorException {
        welcome();

        // TODO - Deal with exceptions
    }

    private static void welcome() throws SQLException, DbConnectorException {
        MBank mBank = MBank.getMBank();
        System.out.println("Welcome to \"Mbank\" - By Ariel Voskov");
        System.out.println("Please login");
        System.out.println("1 - Login as an admin");
        System.out.println("2 - Login as a client");
        System.out.println("q - quit");
        System.out.println("Please enter your selection:");

        String input = multipleChoiceInput(INPUT_OPTIONS);
        switch (Integer.parseInt(input)) {
            case 1:
                if (Login.login(Login.userType.ADMIN)) {
                    AdminActionsMenu.adminActionsClient();
                }
                break;
            case 2:
                if (Login.login(Login.userType.CLIENT)) {
                    ClientActionsMenu.clienActionsClient();
                }
                break;
            case -1:
                System.out.println("Goodbye");
                break;
            case 0:
                System.out.println("There was a problem with the input");
                break;
            default:
                System.out.println("There was a problem with the input");
                break;
        }
    }


}
