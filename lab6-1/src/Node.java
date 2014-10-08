import java.util.ArrayList;

/**
 * The Node class represents a list of symbols and their cumulative frequency.
 *
 * @author Joseph Cumbo
 */
public class Node implements Comparable<Node> {

    // List of symbols under this Node.
    private ArrayList<Symbol> symbols = new ArrayList<Symbol>();
    // Current total frequency of all symbols under this Node.
    private int totalFrequency = 0;

    /**
     * Initializes an empty Node.
     */
    public Node() {
    }

    /**
     * Get the symbols under this Node.
     *
     * @return the symbols under this node.
     */
    public ArrayList<Symbol> getSymbols() {
        return symbols;
    }

    /**
     * Calculates the average variable code length based off the symbol's
     * currently under this Node.
     *
     * @return the average variable code length.
     */
    public double getAvgVariableCodeLength() {
        double averageLength = 0;
        for (Symbol symbol : symbols) {
            averageLength += symbol.getCode().length() * symbol.getFrequency();
        }
        return averageLength / (totalFrequency == 0 ? 1 : totalFrequency);
    }

    /**
     * Calculates the average fixed code length required for representing the
     * symbols currently under this Node.
     *
     * @return the average fixed code length.
     */
    public double getAvgFixedCodeLength() {
        return symbols.isEmpty() ? 0 : Math.ceil(Math.log10(symbols.size()) + 1);
    }

    /**
     * Gets the total frequency of all symbols under this Node.
     *
     * @return the total frequency.
     */
    public int getTotalFrequency() {
        return totalFrequency;
    }

    /**
     * Adds a symbol to this Node. This will update the totalFrequency, adding
     * to it the frequency of the given 'symbol'.
     *
     * @param symbol the symbol to be added.
     */
    public void addSymbol(Symbol symbol) {
        symbols.add(symbol);
        totalFrequency += symbol.getFrequency();
    }

    /**
     * Takes the contents of the given 'node' and adds them to this Node. This
     * is faster then adding each symbol from given 'node' one by one.
     *
     * @param node the node being merged.
     */
    public void addNode(Node node) {
        this.symbols.addAll(node.symbols);
        this.totalFrequency += node.totalFrequency;
    }

    /**
     * Prepends the given 'prefix' to all symbols under this Node.
     *
     * @param prefix the prefix to prepend.
     */
    public void prependCode(String prefix) {
        for (Symbol symbol : symbols) {
            symbol.prependCode(prefix);
        }
    }

    /**
     * Compares this node to a given node.
     *
     * @param node the node that will be compared against.
     * @return 1 if the frequency this Node is greater than the given. 0 if the
     * frequencies are equal. -1 if the frequency of this Node is less than the
     * given.
     */
    @Override
    public int compareTo(Node node) {
        return node.getTotalFrequency() < totalFrequency
                ? 1 : node.getTotalFrequency() > totalFrequency
                        ? -1 : 0;
    }
}
