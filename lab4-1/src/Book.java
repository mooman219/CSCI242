/**
 * The Book class represents the common characteristics of a book, which may be
 * produced in various media formats.
 *
 * @author Joseph Cumbo
 */
public abstract class Book {

    // Everything is initalized because Duncan said to.
    private String title = "";
    private String author = "";
    private double cost = 0d;
    private Media media = Media.Paperback;

    /**
     * The Book constructor instantiates the instance with the specified values.
     *
     * @param title The title of the book.
     * @param author The author of the book.
     * @param cost The cost of the book in cents.
     * @param media The media format of the book.
     */
    public Book(String title, String author, int cost, Media media) {
        this.title = title;
        this.author = author;
        this.cost = cost / 100d;
        this.media = media;
    }

    /**
     *
     * @return the book title enclosed in double quotes(").
     */
    public String getTitle() {
        return '"' + title + '"';
    }

    /**
     *
     * @return the book author name.
     */
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @return the book's cost converted into dollars and cents.
     */
    public double getCost() {
        return cost;
    }

    /**
     * getMedia gets the 'display string representation' of the book's media.
     *
     * @return the book's media.
     */
    public String getMedia() {
        return media.name();
    }

    /**
     * The isForSale implementation depends on the book's media. Some kinds of
     * books are offered only for rent, not for sale.
     *
     * @return true if this instance is for a final sale.
     */
    public abstract boolean isForSale();

    /**
     * The standard string represention prints the title on the first line,
     * followed by the author on the second line, and the book media on the
     * third line. The second and subsequent lines are indented by a single TAB
     * character. Each line should be terminated with a period(.).
     *
     * @return standard string representation of Book.
     */
    @Override
    public String toString() {
        return getTitle() + "."
                + "\n\t" + getAuthor() + "."
                + "\n\t" + media.name();
    }
}
