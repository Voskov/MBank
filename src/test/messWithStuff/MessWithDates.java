package test.messWithStuff;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MessWithDates {
    public static void main(String[] args) throws ParseException {
//        String date = "14-05-18";
//        Date d = new SimpleDateFormat("yy-MM-dd").parse(date);
//        System.out.println(d.toString());
//        Date today = Calendar.getInstance().getTime();
//        System.out.println(today);
//        System.out.println(today.toString());
//        DateFormat df = new SimpleDateFormat("yy-MM-dd");
//        System.out.println("__________________________");
//        System.out.println(df.format(today));
//        System.out.println("***********************");
//        System.out.println("d - " + d.toString() + "; today - " + today.toString());
//        System.out.println(d.compareTo(today));
//        System.out.println("**************************");
//
//        System.out.println(new Date());
//        System.out.println(new Date(1401896199000L));
//
//        System.out.println("++++++++++++++++++++++++++++");
//        Calendar calToday = Calendar.getInstance();
//        System.out.println(calToday);
//        System.out.println(calToday.getTime());
//        calToday.set(Calendar.HOUR, 0);
//        calToday.set(Calendar.MINUTE, 0);
//        calToday.set(Calendar.SECOND, 0);
////        calToday.set(Calendar.MILLISECOND, 0);
//        System.out.println(calToday.getTime());
//        System.out.println("...............................");
////        Calendar addYears = Calendar.getInstance().add(40, Calendar.YEAR);


        String dateStart = "11/03/14 09:29:58";
        String dateStop = "11/03/14 09:33:43";

// Custom date format
        SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");

        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(d1);
        System.out.println(d2);
        assert d1 != null;
        assert d2 != null;
        System.out.println(d2.getTime()-d1.getTime());
    }
}
