import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
        //
        // Process the user's input
        //
        String input = "";
        while (true) {
            System.out.print("Please enter a filename: ");
            input = scanner.nextLine();
            if (input.length() == 0) {
                System.out.println("Invalid file name.");
            } else {
                break;
            }
        }
        System.out.println("");
        //
        // Check if the file exists
        //
        File file = new File(input);
        if (!file.exists()) {
            System.out.println("File '" + input + "' does not exist.");
            return;
        }
        //
        // Generate the output
        //
        VLC vlc = new VLC(file);
        Node result = vlc.calculateEncoding();
        System.out.println("VARIABLE LENGTH CODE OUTPUT");
        System.out.println("---------------------------");
        for (Symbol symbol : result.getSymbols()) {
            System.out.printf("Symbol: %2s  Codeword: %8s  Frequency: %5d\n",
                    symbol.getCharacter(),
                    symbol.getCode(),
                    symbol.getFrequency());
        }
        System.out.println("\nAverage Huffman codeword length: "
                + result.getAvgVariableCodeLength());
        System.out.println("Average Fixed length codeword length: "
                + result.getAvgFixedCodeLength());
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
     * Calculates the variable length encoding for the file. The result is
     * returned in the form of a node.
     *
     * @return the final node leftover.
     * @throws IOException if there is an exception while parsing the file.
     */
    public Node calculateEncoding() {
        ArrayHeap<Node> nodes;
        try {
            nodes = calculateFrequencies();
        } catch (IOException ex) {
            System.out.println("Error reading file.");
            return new Node();
        }
        boolean firstRun = true;
        while (!nodes.isEmpty()) {
            Node right = nodes.removeMin();
            if (nodes.isEmpty()) {
                if (firstRun) { // Edge case
                    right.prependCode("1");
                }
                return right;
            } else {
                Node left = nodes.removeMin();
                right.prependCode("0");
                left.prependCode("1");
                left.addNode(right);
                nodes.add(left);
            }
            firstRun = false;
        }
        System.out.println("Not enough nodes to calculate encoding.");
        return new Node();
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
}
