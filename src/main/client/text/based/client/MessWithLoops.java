package main.client.text.based.client;

public class MessWithLoops {
    public static void main(String[] args) {
        int max = 5;
        int i = 0;
        while (i < max){
            System.out.println("MessWithLoops.main - start");
            System.out.println("i = " + i);
            i++;
            if (i == 2){
                continue;

            }
            System.out.println("MessWithLoops.main - end");
        }

    }
}
