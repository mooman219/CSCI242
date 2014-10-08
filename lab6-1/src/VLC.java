import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
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

        try {
            ArrayHeap<Symbol> heap = calculateFrequencies(file);
        } catch (IOException ex) {
            System.out.println("Error reading file.");
            return;
        }
    }

    /**
     * Parses all the characters in a file and gets the frequency of each one,
     * returning the results in an ArrayHeap.
     *
     * @param file the file being read.
     * @return an ArrayHeap of Symbols.
     * @throws IOException if there is an error while reading the file.
     */
    public static ArrayHeap<Symbol> calculateFrequencies(File file) throws IOException {
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
        ArrayHeap<Symbol> heap = new ArrayHeap<Symbol>();
        for (Symbol sym : symbols.values()) {
            heap.add(sym);
        }
        return heap;
    }

    public VLC(ArrayHeap<Symbol> heap) {

    }

    public static class Node implements Comparable<Node> {

        private int frequency;

        public int getFrequency() {
            return frequency;
        }

        /**
         * Compares this node to a given node.
         *
         * @param sym the symbol that will be compared against.
         * @return 1 if the frequency this Node is greater than the given. 0 if
         * the frequencies are equal. -1 if the frequency of this Node is less
         * than the given.
         */
        @Override
        public int compareTo(Node node) {
            return node.getFrequency() < frequency
                    ? 1 : node.getFrequency() > frequency
                            ? -1 : 0;
        }
    }

    /**
     * The Symbol class represents a character with a frequency.
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
