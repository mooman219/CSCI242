/**
 * The Book class represents the common characteristics of a book, which may be
 * produced in various media formats.
 *
 * @author Joseph Cumbo
 */
public abstract class Book {

    private String title;
    private String author;
    private double cost;
    private Media media;

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
}
