
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
        System.out.println("number of loop cycles: {" + "temp" + "}");
        System.out.println("picture: Picture, filename " + picture.getFileName()
                + " height " + picture.getHeight()
                + " width " + picture.getWidth());
//              width: {480}
//              height: {360}
//              number of loop cycles: {172800}
//              picture: Picture, filename swan.jpg height 360 width 480
    }
}
