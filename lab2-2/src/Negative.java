
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
        System.out.println("width: {" + picture.getWidth() + "}");
        System.out.println("height: {" + picture.getHeight() + "}");
        System.out.println("number of loop cycles: {"
                + (picture.getWidth() * picture.getHeight()) + "}");
        System.out.println("picture: Picture, filename " + picture.getFileName()
                + " height " + picture.getHeight()
                + " width " + picture.getWidth());
    }
}
