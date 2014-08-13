package main.client.text.based.client;

import static main.client.text.based.client.Input.multipleChoiceInput;

public class ClientActions {
    private static String[] INPUT_OPTIONS = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};

    public static void clienActionsClient(){
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
        switch (Integer.parseInt(input)){
            case 1:
                System.out.println("View client details");
                break;
            case 2:
                System.out.println("Update client details");
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
