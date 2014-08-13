package main.client.text.based.client;

import java.util.Scanner;

public class Entry {
    public static void main(String[] args) {
        welcome();
    }

    private static void welcome() {
        System.out.println("Welcome to \"Mbank\" - By Ariel Voskov");
        System.out.println("Please login");
        System.out.println("1 - Login as an admin");
        System.out.println("2 - Login as a client");
        System.out.println("Please enter your selection:");

        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (true) {
            try {
                input = scanner.next();
            } catch (Exception e) {
                //It's okay, no harm done
                //Do nothing
//                input = "";
            }
            if ("1".equals(input) || "2".equals(input)) {
                break;
            }
            System.out.println("Please, choose one of the options above");
            System.out.println("Try again");
        }
        System.out.println(input);
    }
}
