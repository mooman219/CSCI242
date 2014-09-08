
/**
 * @author Joseph Cumbo
 */
public class Negative {

    public static void main(String[] args) {
        Picture picture = new Picture();

        if (args.length < 2
                || !args[0].equalsIgnoreCase("negative")
                || !picture.load(args[1])) {
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

        for (Pixel pixel : picture.getPixels()) {
            pixel.setRed(255 - pixel.getRed());
            pixel.setGreen(255 - pixel.getGreen());
            pixel.setBlue(255 - pixel.getBlue());
        }

        picture.show();

        // Ideal method for closing the program:
        //picture.getPictureFrame().frame
        //        .setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Required method for closing the program:
        try {
            Thread.sleep(3000);
        } catch (InterruptedException exc) {
        }
        System.exit(0);
    }
}
