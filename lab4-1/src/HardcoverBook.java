/**
 * HardcoverBook represents books with hard covers.
 *
 * @author Joseph Cumbo
 */
public class HardcoverBook extends Book {

    private String coverMaterial = "";

    /**
     * The HardcoverBook constructor instantiates the instance by initializing
     * all the fields with the specified values.
     *
     * @param title The title of the book.
     * @param author The author of the book.
     * @param cost The cost of the book in cents.
     * @param media The cover material of the book.
     */
    public HardcoverBook(String title, String author, int cost, String coverMaterial) {
        super(title, author, cost, Media.Hardcover);
        this.coverMaterial = coverMaterial;
    }

    /**
     * The getCoverMaterial method returns the material of the hardcover book.
     *
     * @return string representation of the hardcover material.
     */
    public String getCoverMaterial() {
        return coverMaterial;
    }

    /**
     * The getMedia method extends the standard media information string by
     * adding text describing the cover material of this instance. There is a
     * trailing period(.) at the end of the returned text.
     *
     * @return the book's media.
     */
    @Override
    public String getMedia() {
        return super.getMedia() + " " + coverMaterial + ".";
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
     * The toString represents an HardcoverBook by adding " {cover material}."
     * to the standard string representation of a Book. The {cover material} in
     * this case is the instance's cover material. There is a trailing period(.)
     * at the end of the returned text.
     *
     * @return string representation of this electronic book
     */
    @Override
    public String toString() {
        return super.toString() + " " + coverMaterial + ".";
    }
}
