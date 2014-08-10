package test.messWithStuff;

public class ParseDoubles {
    public static void main(String[] args) {
        Double d = 1234.567;
        String t = d.toString();
        Double c = Double.parseDouble(t);
        System.out.println(d + " - " + d.getClass());
        System.out.println(t + " - " + t.getClass());
        System.out.println(c + " - " + c.getClass());
        System.out.println("__________________________");



        System.out.println(d + " - " + d.getClass());


    }
}
