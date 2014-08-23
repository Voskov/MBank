package main.client.text.based.client;

import main.exceptions.DbConnectorException;
import main.model.Client;
import main.services.AdminAction;
import main.services.impl.AdminActionImpl;

import static main.client.text.based.client.Input.anotherAction;
import static main.client.text.based.client.Input.multipleChoiceInput;

public class AdminActionsMenu {

    private static String[] INPUT_OPTIONS = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};

    public static void adminActionsClient() throws DbConnectorException {
        boolean performAnAction = true;
        while (performAnAction) {
            System.out.println("Admin Actions");
            System.out.println("-------------");
            System.out.println("Please choose one of the following:");
            System.out.println("1 - Add a new client");
            System.out.println("2 - Update client details");
            System.out.println("3 - Remove a client");
            System.out.println("4 - Create a new account");
            System.out.println("5 - Remove account");
            System.out.println("6 - View client details");
            System.out.println("7 - View all client details");
            System.out.println("8 - View account details");
            System.out.println("9 - View all accounts details");
            System.out.println("10 - View a deposit");
            System.out.println("11 - View all deposits");
            System.out.println("12 - View client activities");
            System.out.println("13 - View all activities");
            System.out.println("14 - View a system property");
            System.out.println("15 - Update a system property");
            System.out.println("Please enter your selection");

            String input = multipleChoiceInput(INPUT_OPTIONS);
            switch (Integer.parseInt(input)) {
                case 1:
                    addNewClient();
                    break;
                case 2:
                    System.out.println("Update client details");
                    break;
                case 3:
                    System.out.println("Remove a client");
                    break;
                case 4:
                    System.out.println("Create a new account");
                    break;
                case 5:
                    System.out.println("Remove account");
                    break;
                case 6:
                    System.out.println("View client details");
                    break;
                case 7:
                    System.out.println("View all client details");
                    break;
                case 8:
                    System.out.println("View account details");
                    break;
                case 9:
                    System.out.println("View all accounts details");
                    break;
                case 10:
                    System.out.println("View a deposit");
                    break;
                case 11:
                    System.out.println("View all deposits");
                    break;
                case 12:
                    System.out.println("View client activities");
                    break;
                case 13:
                    System.out.println("View all activities");
                    break;
                case 14:
                    System.out.println("View a system property");
                    break;
                case 15:
                    System.out.println("Update a system property");
                    break;
                default:
                    System.out.println("There was a problem");
                    break;
            }
            performAnAction = anotherAction();
        }
    }

    private static void addNewClient() throws DbConnectorException {
        System.out.println("Add a new client");
        System.out.println("----------------");
        System.out.println("Please, prepare a username, a password, an address, en email and a phone number");
        System.out.println("Please enter a user name");
        String username = Input.stringInput();
        System.out.println("Please enter a password");
        String password = Input.stringInput();
        System.out.println("Please enter a address");
        String address = Input.stringInput();
        System.out.println("Please enter a email");
        String email = Input.stringInput();
        System.out.println("Please enter a phone");
        String phone = Input.stringInput();
        System.out.println("Please enter a comment");
        String comment = Input.stringInput();
        System.out.println("How much money is the new client would like to deposit?");
        Long amount = Input.longInput();

        Client newClient = new Client(username, password, address, email, phone, comment);
        AdminAction aa = new AdminActionImpl();
        aa.addNewClient(newClient, amount);
    }
}
