package main.client.text.based.client;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Input {
    private static final int RETRIES = 3;

    public static String multipleChoiceInput(String[] input_options) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        int attempt = 0;

        while (attempt < RETRIES) {
            try {
                input = scanner.next();
            } catch (Exception e) {
                //It's okay, no harm done
                //Do nothing
            }
            if (Arrays.asList(input_options).contains(input)) {
                break;
            }
            System.out.println("Please, choose one of the options above");
            System.out.println("Try again");
            attempt++;
            input = "0";
        }
        return input;
    }
}
