package test.messWithStuff;

/**
 * Created by Ariel Voskov on 5/26/2014.
 */
public class MessWithStringBuilder {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("test");
        sb.append(" one");
        sb.append(" two");
        System.out.println(sb.toString());
        System.out.println(sb.length());
    }
}
