package test.messWithStuff;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ariel Voskov on 5/18/2014.
 */
public class MessWithDates {
    public static void main(String[] args) throws ParseException {
        String date = "14-05-18";
        Date d = new SimpleDateFormat("yy-MM-dd").parse(date);
        System.out.println(d.toString());
        Date today = Calendar.getInstance().getTime();
        System.out.println(today);
        System.out.println(today.toString());
        DateFormat df = new SimpleDateFormat("yy-MM-dd");
        System.out.println("__________________________");
        System.out.println(df.format(today));
        System.out.println("***********************");
        System.out.println(d.compareTo(today));
    }
}
