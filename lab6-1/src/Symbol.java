/**
 * The Symbol class represents a character with a frequency.
 *
 * @author Joseph Cumbo
 */
public class Symbol {

    // The caracter this Symbol represents
    private final char character;
    // The frequency of the 'character'
    private int frequency;
    // The code for the Symbol
    private String code = "";

    /**
     * Creates a symbol class.
     *
     * @param character the 'character' this Symbol is representing.
     * @param frequency the frequency of the given 'character'.
     */
    public Symbol(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    /**
     * Gets the character this symbol is representing.
     *
     * @return the character representing this Symbol.
     */
    public char getCharacter() {
        return character;
    }

    /**
     * Gets the frequency of the character this Symbol represents.
     *
     * @return the frequency of the character this Symbol represents.
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Returns the current code for this symbol.
     *
     * @return the code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Increments the frequency of this symbol by 1.
     */
    public void incrementFrequency() {
        frequency++;
    }

    /**
     * Prepends the current code with the given 'prefix'.
     *
     * @param prefix the prefix to prepend.
     */
    public void prependCode(String prefix) {
        this.code = prefix + code;
    }
}
