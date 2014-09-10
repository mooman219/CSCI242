
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

        PictureBorder.addBorder(picture);
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

    public static void addBorder(Picture picture) {
        // For my frame, I'm using a % based system.
        // I will multiply the width and height of the image and multiply both
        // by 2.5%, then storing the resulting values in the width and height
        // objects. This will be used to check wether a pixel falls in the outer
        // 2.5% of pixels.
        int width = (int) (picture.getWidth() * 0.025);
        int height = (int) (picture.getHeight() * 0.025);
        // Now I am going to loop through every pixel in the image
        for (int x = 0; x < picture.getWidth(); x++) {
            for (int y = 0; y < picture.getHeight(); y++) {
                // This statement will check if the pixel falls in the outer
                // 2.5% of pixels in the image.
                if (x < width || x > (picture.getWidth() - width)
                        || y < height || y > (picture.getHeight() - height)) {
                    // Finally, we invert the pixel
                    Pixel pixel = picture.getPixel(x, y);
                    pixel.setRed(255 - pixel.getRed());
                    pixel.setGreen(255 - pixel.getGreen());
                    pixel.setBlue(255 - pixel.getBlue());
                }
            }
        }
    }
}
