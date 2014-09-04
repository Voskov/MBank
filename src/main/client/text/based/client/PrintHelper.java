package main.client.text.based.client;

public class PrintHelper {
    public static void ptintLeft(String string, int width) {
        System.out.print("| ");
        System.out.print(string);
        for (int i = string.length() + 1; i < width; i++) {
            System.out.print(" ");
        }
    }
}
