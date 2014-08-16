package main.client.text.based.client;

import static main.client.text.based.client.Input.multipleChoiceInput;

public class ClientActionsMenu {
    private static final int RETRIES = 3;
    private static String[] INPUT_OPTIONS = {"1", "2", "3", "4", "5", "6"};

    public static void clienActionsClient() {
        System.out.println("Client Actions");
        System.out.println("--------------");
        System.out.println("Please choose one of the following:");
        System.out.println("1 - View client details");
        System.out.println("2 - Update client details");
        System.out.println("3 - View account details");
        System.out.println("4 - View deposits");
        System.out.println("5 - View activities");
        System.out.println("6 - View properties");
        System.out.println("Please enter your selection");

        String input = multipleChoiceInput(INPUT_OPTIONS);
        switch (Integer.parseInt(input)) {
            case 1:
                System.out.println("View client details");
                break;
            case 2:
                System.out.println("Update client details");
                break;
            case 3:
                System.out.println("View account details");
                break;
            case 4:
                System.out.println("View deposits");
                break;
            case 5:
                System.out.println("View activities");
                break;
            case 6:
                System.out.println("View properties");
                break;
            default:
                System.out.println("There was a problem");
                break;
        }
    }

    public static void main(String[] args) {
        clienActionsClient();
    }
}
