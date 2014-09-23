/**
 * PaperbackBook represents a basic paperback book.
 *
 * @author Joseph Cumbo
 */
public class PaperbackBook extends Book {

    /**
     * The PaperbackBook constructor instantiates and initializes the instance.
     *
     * @param title The title of the book.
     * @param author The author of the book.
     * @param cost The cost of the book in cents.
     */
    public PaperbackBook(String title, String author, int cost) {
        super(title, author, cost, Media.Paperback);
    }

    /**
     * The isForSale implementation depends on the book's media. Some kinds of
     * books are offered only for rent, not for sale.
     *
     * @return true if this instance is for a final sale.
     */
    @Override
    public boolean isForSale() {
        return true;
    }

    /**
     * toString adds a trailing period(.) at the end of the returned text.
     *
     * @return string representation of this book
     */
    @Override
    public String toString() {
        return super.toString() + ".";
    }
}
