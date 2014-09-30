/*
 * BrowserUtil.java
 */

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;

/**
 * Utilities for displaying data in a web browser
 * 
 * @author James Heliotis
 * @version $Id: BrowserUtil.java,v 1.5 2014/02/02 22:23:10 jeh Exp $
 *
 */
public class BrowserUtil {

	/**
	 * This class should not be instantiated.
	 * It contains static utility functions.
	 */
	private BrowserUtil() {}

    public static final String defaultFileName;

    /**
     * WARNING: change the default file name if it will override
     * a file of importance to you!
     */
    static {
        defaultFileName =
                System.getProperty( "user.home" ) +
                File.separatorChar +"temp.html";
    }

    public static final String HTML_HEADER =
            "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\"" +
            "\"http://www.w3.org/TR/html4/strict.dtd\">\n";

    /**
     * Save the provided string as the entire contents of a file, then
     * have this desktop's default browser display the file as HTML.
     * 
     * @param html the string containing the HTML text.
     * 
     * Preconditions:
     *   The string should contain legal HTML.
     *   The string's outermost tag should be "<html>...</html>".
     */
    public static void render( String html ) {
        if ( Desktop.isDesktopSupported() ) {
            try {
                FileWriter fw = new FileWriter( defaultFileName );
                fw.write( HTML_HEADER );
                fw.write( html );
                fw.close();
            }
            catch ( IOException ioe ) {
                System.err.println( ioe );
                ioe.printStackTrace();
            }
            Desktop dt = Desktop.getDesktop();
            URI uri;
            try {
                uri = new File( defaultFileName ).toURI();
                dt.browse( uri );
            }
            catch ( IOException e ) {
                System.err.println(
                        "Error: cannot open file://" + defaultFileName + '.'
                      );
            }
        }
        else {
            System.err.println(
              "Error: unable to open browser in this environment."
            );
        }
    }

    /**
     * This method contains a test for the render method.
     * @param args not used
     */
    public static void main( String[] args ) {
        BrowserUtil.render(
          "<html><head></head><body><h1>It works!</h1></body></html>"
        );
    }
}

/*
 * Revisions:
 * $Log: BrowserUtil.java,v $
 * Revision 1.5  2014/02/02 22:23:10  jeh
 * More debugging, commenting, and improvement of the three test programs.
 * Added Diagnostics class to hold useful test utilities.
 * [JEH]
 *
 * Revision 1.4  2014/01/20 17:54:55  jeh
 * More minor javadoc updates
 *
 * Revision 1.3  2014/01/20 17:47:25  jeh
 * Added javadoc to the DocObject interface.
 *
 * Revision 1.2  2014/01/20 05:11:30  jeh
 * (Hopefully) fixed BrowserUtil so it works in Windows.
 *
 */
