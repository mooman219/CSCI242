/**
 * AudioBook is a book delivered as audio compact discs (CD).
 *
 * @author Joseph Cumbo
 */
public class AudioBook extends Book {

    private int numDiscs = 0;

    /**
     * The constructor instantiates this particular kind of Book instance.
     *
     * @param title The title of the book.
     * @param author The author of the book.
     * @param cost The cost of the book in cents.
     * @param media The media format of the book.
     */
    public AudioBook(String title, String author, int cost, int numDiscs) {
        super(title, author, cost, Media.Audio);
        this.numDiscs = numDiscs;
    }

    /**
     * The getMedia method adds the number of discs to the string representation
     * of this instance's media format.
     *
     * @return the book's media.
     */
    @Override
    public String getMedia() {
        return super.getMedia() + ": " + numDiscs + " discs.";
    }

    /**
     * AudioBook instances are never offered for sale; they are rental only.
     *
     * @return true if this instance is for a final sale.
     */
    @Override
    public boolean isForSale() {
        return false;
    }

    /**
     * The toString represents an AudioBook by adding ": {n} disks." to the
     * standard string representation of a Book. The {n} in this case is the
     * number of discs.
     *
     * @return string representation including the number of discs for the book.
     */
    @Override
    public String toString() {
        return super.toString() + ": " + numDiscs + " discs.";
    }
}
