package test.messWithStuff;

import java.util.DoubleSummaryStatistics;

public class MessWithConvertion {
    public static void main(String[] args) {


        String st = "0.05";
        Double d = Double.valueOf(st);
        System.out.println(d);
        System.out.println(d.getClass());

        Double e = Double.valueOf("sdfghjk");
    }
}
