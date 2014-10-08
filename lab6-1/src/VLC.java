import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The VLC class will calculate the optimal variable length encoding for a given
 * file.
 *
 * @author Joseph Cumbo
 */
public class VLC {

    // Scanner used for getting the user's input.
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Standard main method.
     *
     * @param args argument not used.
     */
    public static void main(String[] args) {
        String input = "";
        while (true) {
            System.out.print("Please enter symbol filename: ");
            input = scanner.nextLine();
            if (input.length() == 0) {
                System.out.println("Invalid file name.");
            } else {
                break;
            }
        }

        File file = new File(input);
        if (!file.exists()) {
            System.out.println("File '" + input + "' does not exist.");
            return;
        }
    }

    // The file that will be read.
    private final File file;

    /**
     * Initializes a VLC with a given 'file'.
     *
     * @param file the file that will be read from.
     */
    public VLC(File file) {
        this.file = file;
    }

    /**
     * Parses all the characters in a file and gets the frequency of each one,
     * returning the results in an ArrayHeap.
     *
     * @param file the file being read.
     * @return an ArrayHeap of Symbols.
     * @throws IOException if there is an error while reading the file.
     */
    public ArrayHeap<Node> calculateFrequencies() throws IOException {
        //
        // Build a map of the characters
        //
        BufferedReader reader = new BufferedReader(new FileReader(file));
        HashMap<Character, Symbol> symbols = new HashMap<Character, Symbol>();
        String line;
        while ((line = reader.readLine()) != null) {
            for (int i = 0; i < line.length(); i++) {
                char character = line.charAt(i);
                Symbol symbol = symbols.get(character);
                if (symbol == null) {
                    symbol = new Symbol(character, 0);
                    symbols.put(character, symbol);
                }
                symbol.incrementFrequency();
            }
        }
        reader.close();
        //
        // Use the map to build an ArrayHeap which is required in the lab.
        //
        ArrayHeap<Node> heap = new ArrayHeap<Node>();
        for (Symbol sym : symbols.values()) {
            Node node = new Node();
            node.addSymbol(sym);
            heap.add(node);
        }
        return heap;
    }

    /**
     * The Node class represents a list of symbols and their cumulative
     * frequency.
     *
     * @author Joseph Cumbo
     */
    public static class Node implements Comparable<Node> {

        // List of symbols under this Node.
        private ArrayList<Symbol> symbols = new ArrayList<Symbol>();
        // Current total frequency of all symbols under this Node.
        private int totalFrequency;

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
        public double calculateAverageVariableCodeLength() {
            double averageLength = 0;
            for (Symbol symbol : symbols) {
                averageLength += symbol.getCode().length();
            }
            return averageLength / 4;
        }

        /**
         * Calculates the average fixed code length required for representing
         * the symbols currently under this Node.
         *
         * @return the average fixed code length.
         */
        public double calculateAverageFixedCodeLength() {
            return Math.ceil(Math.log10(symbols.size()));
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
         * Adds a symbol to this Node. This will update the totalFrequency,
         * adding to it the frequency of the given 'symbol'.
         *
         * @param symbol the symbol to be added.
         */
        public void addSymbol(Symbol symbol) {
            symbols.add(symbol);
            totalFrequency += symbol.getFrequency();
        }

        /**
         * Takes the contents of the given 'node' and adds them to this Node.
         * This is faster then adding each symbol from given 'node' one by one.
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
         * @return 1 if the frequency this Node is greater than the given. 0 if
         * the frequencies are equal. -1 if the frequency of this Node is less
         * than the given.
         */
        @Override
        public int compareTo(Node node) {
            return node.getTotalFrequency() < totalFrequency
                    ? 1 : node.getTotalFrequency() > totalFrequency
                            ? -1 : 0;
        }
    }

    /**
     * The Symbol class represents a character with a frequency.
     *
     * @author Joseph Cumbo
     */
    public static class Symbol {

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
}
