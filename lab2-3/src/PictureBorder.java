
import java.awt.Color;

/**
 * @author Joseph Cumbo
 */
public class PictureBorder {

    public static void main(String[] args) {
        Picture picture = new Picture();

        if (args.length < 1 || !picture.load(args[0])) {
            System.out.println("usage: java JPGfile");
            return;
        }

        System.out.println("width: {" + picture.getWidth() + "}");
        System.out.println("height: {" + picture.getHeight() + "}");
        System.out.println("number of loop cycles: {"
                + (picture.getWidth() * picture.getHeight()) + "}");
        System.out.println("picture: Picture, filename " + picture.getFileName()
                + " height " + picture.getHeight()
                + " width " + picture.getWidth());

        PictureBorder.addBorder(picture, Color.red, 20);
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

    /**
     * Adds a border around the image with given color and width.
     *
     * @param picture The underlying image.
     * @param color The color of the border around the image.
     * @param width The width of the border around the image.
     */
    public static void addBorder(Picture picture, Color color, int width) {
        // Loop through every pixel in the image.
        for (int x = 0; x < picture.getWidth(); x++) {
            for (int y = 0; y < picture.getHeight(); y++) {
                // This statement will check if the pixel falls in the border
                // width.
                if (x < width || x > (picture.getWidth() - width)
                        || y < width || y > (picture.getHeight() - width)) {
                    // Set the pixel to the given color.
                    Pixel pixel = picture.getPixel(x, y);
                    pixel.setColor(color);
                }
            }
        }
    }
}
