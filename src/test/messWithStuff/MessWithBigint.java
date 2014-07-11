package test.messWithStuff;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MessWithBigint {
    public static void main(String[] args) {

        BigDecimal sum = BigDecimal.valueOf(0);
        for (int i = 0; i < 10; i++) {
            sum.add(new BigDecimal("0.0001"));
        }
        System.out.println(sum);
        System.out.println("*********************");
        int a = -1000;
        System.out.println(Math.abs(a));
    }
}
