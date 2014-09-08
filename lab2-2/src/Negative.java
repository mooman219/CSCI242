
/**
 * @author Joseph Cumbo
 */
public class Negative {

    public static void main(String[] args) {
        Picture picture = new Picture();
        if (args.length < 1 || !picture.load(args[0])) {
            System.out.println("usage: java Negative JPGfile");
            return;
        }
    }
}
