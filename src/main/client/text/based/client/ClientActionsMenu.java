package main.client.text.based.client;

import main.db_access_layer.managers.ActivityManager;
import main.db_access_layer.managers.DepositManager;
import main.db_access_layer.managers.impl.ActivityManagerImpl;
import main.db_access_layer.managers.impl.DepositManagerImpl;
import main.exceptions.DbConnectorException;
import main.model.Activity;
import main.model.Client;

import java.sql.SQLException;
import java.util.HashSet;

import static main.client.text.based.client.Input.anotherAction;
import static main.client.text.based.client.Input.multipleChoiceInput;

public class ClientActionsMenu {
    private static final int RETRIES = 3;
    private static String[] INPUT_OPTIONS = {"1", "2", "3", "4", "5", "6"};

    public static void clientActionsClient(Client client) throws DbConnectorException {
        boolean performAnAction = true;
        while (performAnAction) {
            System.out.println("Client Actions");
            System.out.println("--------------");
            System.out.println("Please choose one of the following:");
            System.out.println("1 - View client details");
            System.out.println("2 - Update client details");
            System.out.println("3 - View account details");
            System.out.println("4 - View deposits");
            System.out.println("5 - View activities");
            System.out.println("6 - View properties");
            System.out.println("q - quit");
            System.out.println("Please enter your selection");

            String input = multipleChoiceInput(INPUT_OPTIONS);
            switch (Integer.parseInt(input)) {
                case 1:
                    viewClientDetails(client);
                    break;
                case 2:
                    updateClientDetails(client);
                    break;
                case 3:
                    viewAccountDetails(client);
                    break;
                case 4:
                    viewDeposits(client);
                    break;
                case 5:
                    viewActivities(client);
                    break;
                case 6:
                    viewProperties(client);
                    break;
                case -2:
                    //uhm... nothing.
                    break;
                default:
                    System.out.println("There was a problem");
                    break;
            }
            performAnAction = anotherAction();
        }
    }

    private static void viewProperties(Client client) throws DbConnectorException {
        System.out.println("View properties");
        System.out.println("---------------");
        client.toString();
    }

    private static void viewActivities(Client client) throws DbConnectorException {

        System.out.println("View activities");
        System.out.println("---------------");
        ActivityManager am = new ActivityManagerImpl();
        String[] headers = new String[]{"Date", "Amount", "Commission", "Activity"};
        int[] widths = new int[]{30, 20, 12, 60};
        try {
            HashSet<Activity> clientsActivities = am.findAllClientActivities(client.getClientId());
            if (clientsActivities == null || clientsActivities.isEmpty()) {
                System.out.println("this client doesn't have any activities");  //This shouldn't happen
                return;
            }
            printHeaders(headers, widths);
            for (Activity activity : clientsActivities) {
                printActivity(activity, widths);
            }
            printFooter(widths);
        } catch (SQLException e) {
            System.out.println("this client doesn't have any activities");  //This shouldn't happen
        }
    }



    private static void printFooter(int[] widths) {
        for (int width : widths) {
            System.out.print("|");
            for (int i = 0; i < width; i++) {
                System.out.print("-");
            }
        }
        System.out.print("|");
        System.out.println("");
    }

    private static void printActivity(Activity activity, int[] widths) {
        System.out.print("|");
        System.out.print(activity.getActivityDate());   // TODO - printInMiddle
        PrintHelper.ptintLeft(activity.getActivityDate(), widths[0]);
        PrintHelper.ptintLeft(String.valueOf(activity.getAmount()), widths[1]);
        PrintHelper.ptintLeft(String.valueOf(activity.getCommission()), widths[2]);
        PrintHelper.ptintLeft(activity.getDescription(), widths[3]);
        System.out.print("|");
        System.out.println("");
    }

    private static void printHeaders(String[] headers, int[] widths) {
        if (headers.length != widths.length) {
            throw new InternalError("Column names and widths aren't equal");
        }
        printFooter(widths);
        for (int i = 0; i < headers.length; i++) {
            System.out.println("|");
            // Print header in the middle
        }
        System.out.print("|");
        System.out.println("");
        printFooter(widths);
    }

    private static void viewDeposits(Client client) throws DbConnectorException {
        System.out.println("View deposits");
        System.out.println("-------------");
        DepositManager dm = new DepositManagerImpl();
        HashSet allClientsDeposits = dm.allClientsDeposits(client.getClientId());

    }

    private static void viewAccountDetails(Client client) throws DbConnectorException {
        System.out.println("View account details");
        System.out.println("--------------------");
    }

    private static void updateClientDetails(Client client) throws DbConnectorException {
        System.out.println("Update client details");
        System.out.println("---------------------");
        System.out.println("What would you like to update?");
        System.out.println("1 - password");
        System.out.println("2 - address");
        System.out.println("3 - email");
        System.out.println("4 - phone");
        System.out.println("5 - comment");
        String input = Input.multipleChoiceInput(new String[]{"1", "2", "3", "4", "5", "6"});
        switch (Integer.valueOf(input)){
            case 1:
                System.out.println("Please enter your old password");
                String oldPassword = Input.stringInput();
                System.out.println("Please enter a new password");
                String newPassword1 = Input.stringInput();
                System.out.println("Please enter the new password again");
                String newPassword2 = Input.stringInput();
                if (!newPassword1.equals(newPassword2)){
                    System.out.println("The passwords you've typed did not match");
                    return;
                }
                if (!client.getPassword().equals(oldPassword)){
                    System.out.println("The password you've entered is incorrect");
                    return;
                }

        }

    }

    private static void viewClientDetails(Client client) throws DbConnectorException {
        System.out.println("View client details");
        System.out.println("-------------------");
        System.out.println(client.toString());
        System.out.println("");
    }

}
