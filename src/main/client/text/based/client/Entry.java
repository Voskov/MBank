package main.client.text.based.client;

import main.exceptions.DbConnectorException;
import main.services.MBank;

import java.sql.SQLException;
import java.sql.Time;

import static main.client.text.based.client.Input.multipleChoiceInput;

public class Entry {
    private static String[] INPUT_OPTIONS = {"1", "2"};

    public static void main(String[] args) throws InterruptedException {
        try {
            welcome();


        } catch (SQLException e) {
            e.printStackTrace();
            Thread.sleep(700);      //So the message won't get mixed with the stackTrace output
            System.out.println("-----------------------------");
            System.out.println("A database error has occurred.");
            System.out.println("MBank will now close");
        } catch (DbConnectorException e) {
            e.printStackTrace();
            Thread.sleep(700);
            System.out.println("---------------------");
            System.out.println("An error has occurred.");
            System.out.println("Please chech that the database server is running");
            System.out.println("MBank will now close");
        } catch (Exception e) {
            e.printStackTrace();
            Thread.sleep(700);
            System.out.println("--------------------------------");
            System.out.println("An unexpected error has occurred.");
            System.out.println("MBank will now exit");
        }

        try {
            //Finish and close connections
            MBank mBank = MBank.getMBank();
            mBank.getPool().drainConnectionPool();
        } catch (DbConnectorException e) {
            e.printStackTrace();
        }
        Thread.sleep(300);
        System.out.println("Thank you and goodbye");
    }

    private static void welcome() throws SQLException, DbConnectorException, InterruptedException {
        MBank mBank = MBank.getMBank();
        Thread.sleep(400);
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
            case -2:
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
