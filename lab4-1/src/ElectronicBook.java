/**
 * ElectronicBook represents an electronic book instance, an e-book.
 *
 * @author Joseph Cumbo
 */
public class ElectronicBook extends Book {

    private String theURL = "";

    /**
     * The ElectronicBook constructor instantiates the instance and initializes
     * all its fields using the supplied arguments.
     *
     * @param title The title of the book.
     * @param author The author of the book.
     * @param cost The cost of the book in cents.
     * @param media The URL of the book, which must contain the text "://".
     */
    public ElectronicBook(String title, String author, int cost, String theURL) {
        super(title, author, cost, Media.Electronic);
        this.theURL = theURL;
    }

    /**
     * The getMedia method adds the number of discs to the string representation
     * of this instance's media format.
     *
     * @return the book's media.
     */
    @Override
    public String getMedia() {
        return super.getMedia() + " : from " + theURL;
    }

    /**
     * ElectronicBook instances are never offered for sale. You may rent them
     * instead.
     *
     * @return true if this instance is for a final sale.
     */
    @Override
    public boolean isForSale() {
        return false;
    }

    /**
     * The toString represents an ElectronicBook by adding " from {URL}" to the
     * standard string representation of a Book. The {URL} in this case is the
     * resource locator of this instance.
     *
     * @return string representation of this electronic book
     */
    @Override
    public String toString() {
        return super.toString() + " from " + theURL + ".";
    }
}
