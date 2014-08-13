package main.client.text.based.client;

public class ClientActions {
    private static String[] INPUT_OPTIONS = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};

    public static void clienActionsClient(){
        System.out.println("Client Actions");
        System.out.println("--------------");
        System.out.println("Please choose one of the following:");
        System.out.println("1 - Update details");
        System.out.println("2 - Update details");
        System.out.println("3 - View account details");
        System.out.println("4 - View deposits");
        System.out.println("5 - View activities");
        System.out.println("6 - View properties");
    }

    public static void main(String[] args) {
        clienActionsClient();
    }
}
