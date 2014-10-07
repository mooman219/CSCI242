import java.io.File;
import java.util.Scanner;

/**
 * @author Joseph Cumbo
 */
public class VLC {

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

    public VLC() {

    }

    public static class Symbol implements Comparable<Symbol> {

        // The caracter this class represents
        private final char character;
        // The frequency of the 'character'
        private final int frequency;

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
