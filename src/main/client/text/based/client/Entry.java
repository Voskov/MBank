package main.client.text.based.client;

import static main.client.text.based.client.Input.multipleChoiceInput;

public class Entry {
    private static String[] INPUT_OPTIONS = {"1", "2"};

    public static void main(String[] args) {
        welcome();
    }

    private static void welcome() {
        System.out.println("Welcome to \"Mbank\" - By Ariel Voskov");
        System.out.println("Please login");
        System.out.println("1 - Login as an admin");
        System.out.println("2 - Login as a client");
        System.out.println("Please enter your selection:");

        String input = multipleChoiceInput(INPUT_OPTIONS);
        System.out.println(input);
        switch (Integer.parseInt(input)){
            case 1:

                ClientActions.clienActionsClient();
                break;
            default:
                System.out.println("There was a problem with the input");
        }
    }


}
