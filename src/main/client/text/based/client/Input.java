package main.client.text.based.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Input {
    private static final int RETRIES = 3;
    private static final String QUIT = "q";
    private static final String BACK = "b";
    private static final String[] ANOTHER_ACTION_OPTIONS = {"y", "n"};
    private static Scanner scanner = new Scanner(System.in);

    public static String multipleChoiceInput(String[] input_options) {
        ArrayList<String> options = new ArrayList<String>(Arrays.asList(input_options));
        options.add(BACK);
        options.add(QUIT);
        String input = "";
        int attempt = 0;

        while (attempt < RETRIES) {
            try {
                input = scanner.next();
            } catch (Exception e) {
                //It's okay, no harm done
                //Do nothing
            }
            if (options.contains(input)) {
                if (BACK.equals(input)) {
                    input = "-1";
                } else if (QUIT.equals(input)) {
                    input = "-2";
                }
                break;
            }
            System.out.println("Please, choose one of the options above");
            System.out.println("Try again");
            attempt++;
            input = "0";
        }
        return input;
    }

    public static boolean anotherAction() {
        System.out.println("Would you like to do anything else? (y/n)");
        String input = multipleChoiceInput(ANOTHER_ACTION_OPTIONS);
        if ("y".equals(input)) {
            return true;
        } else if ("n".equals(input)) {
            return false;
        } else {    // I do realize I could have just return the "y".equality. perhaps I will...
            return false;
        }
    }

    public static String stringInput() {
        String input = "";
        int attempt = 0;
        while ("".equals(input) && attempt < RETRIES) {
            input = scanner.next();
            if (input.trim().isEmpty()) {
                System.out.println("Please, enter a valid input");
            }
        }
        return input;
    }

    public static Long longInput() {
        Long input = null;
        int attempt = 0;
        while (attempt < RETRIES) {
            try {
                input = scanner.nextLong();
                break;
            } catch (Exception e) {
                // I'll ignore your attempts to brake this for now
            }
        }
        return input;
    }

    public static Double doubleInput() {
        Double input = null;
        int attempt = 0;
        while (attempt < RETRIES) {
            try {
                input = scanner.nextDouble();
                break;
            } catch (Exception e) {
                // I'll ignore your attempts to brake this for now
            }
        }
        return input;
    }
}
