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

        private final char character;
        private final int frequency;

        public Symbol(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
        }

        public char getCharacter() {
            return character;
        }

        public int getFrequency() {
            return frequency;
        }

        @Override
        public int compareTo(Symbol o) {
            return o.getFrequency() < frequency
                    ? -1 : o.getFrequency() > frequency
                            ? 1 : 0;
        }
    }
}
