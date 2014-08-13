package main.client.text.based.client;

import java.util.Scanner;

public class Login {
   public static void clientLogin(){
       Scanner scanner = new Scanner(System.in);
       System.out.println("Please enter your user name");
       String username = scanner.next();
       System.out.println("Please enter your password");
       String password = scanner.next();
   }

}
