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

    public static ArrayHeap<Symbol> calculateFrequencies(File file) throws IOException {
        ArrayHeap<Symbol> heap = new ArrayHeap<Symbol>();
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        HashMap<Character, Integer> symbols = new HashMap<Character, Integer>();
        while ((line = reader.readLine()) != null) {
            for (int i = 0; i < line.length(); i++) {
                Integer frequency = symbols.get(line.charAt(i));
                if () {

                }
            }
        }
    }

    public VLC() {

    }

    /**
     * The Symbol class represents a character with a frequency.
     */
    public static class Symbol implements Comparable<Symbol> {

        // The caracter this class represents
        private final char character;
        // The frequency of the 'character'
        private int frequency;

        /**
         * Creates a symbol class.
         *
         * @param character the 'character' this class is representing.
         * @param frequency the frequency of the given 'character'.
         */
        public Symbol(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
        }

        /**
         * Gets the character this symbol is representing.
         *
         * @return the character representing this class.
         */
        public char getCharacter() {
            return character;
        }

        /**
         * Gets the frequency of the character this class represents.
         *
         * @return the frequency of the character this class represents.
         */
        public int getFrequency() {
            return frequency;
        }

        /**
         * Increments the frequency of this symbol by 1.
         */
        public void incrementFrequency() {
            frequency++;
        }

        /**
         * Compares this symbol to a given symbol.
         *
         * @param sym the symbol that will be compared against.
         * @return -1 if the frequency of 'sym' is less than the frequency of
         * this class. 0 if the frequencies are equal. 1 if the frequency of
         * 'sym' is greater than this class.
         */
        @Override
        public int compareTo(Symbol sym) {
            return sym.getFrequency() < frequency
                    ? -1 : sym.getFrequency() > frequency
                            ? 1 : 0;
        }
    }
}
