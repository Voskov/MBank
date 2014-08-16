package main.client.text.based.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Input {
    private static final int RETRIES = 3;
    private static final String QUIT = "q";
    private static final String BACK = "b";
    public static String multipleChoiceInput(String[] input_options) {
        ArrayList<String> options = new ArrayList<String>(Arrays.asList(input_options));
        options.add(BACK);
        options.add(QUIT);
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
            if (options.contains(input)) {
                if (BACK.equals(input)){
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
}
