package test.messWithStuff;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MessWithScanner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in) ;
        System.out.println("Enter number");
        try {
            int num = sc.nextInt();
            System.out.println("You've entered " + num);
        } catch (InputMismatchException e){
            System.out.println("You were supposed to enter a number!!!");
        }
    }
}
