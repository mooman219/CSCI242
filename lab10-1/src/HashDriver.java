import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * The HashDriver class takes a file as an argument and hashes the words in the
 * file.
 *
 * @author Joseph Cumbo (mooman219)
 */
public class HashDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            System.out.println("Buiding tables for '" + args[0] + "'.");
            HashTable simpleTable = new HashTable(Hash.Htype.SIMPLE, 10000);
            HashTable customTable = new HashTable(Hash.Htype.CUSTOM, 10000);
            try (Scanner scanner = new Scanner(new FileReader(args[0]));) {
                int words = 0;
                while (scanner.hasNext()) {
                    String word = scanner.next();
                    simpleTable.put(word);
                    customTable.put(word);
                    words++;
                }
                System.out.println("Total words in '" + args[0] + "' is " + words + ".");
                System.out.println("Simple hash imbalance was " + simpleTable.imbalance()
                        + " with " + simpleTable.size() + " total elements in the table.");
                System.out.println("Custom hash imbalance was " + customTable.imbalance()
                        + " with " + customTable.size() + " total elements in the table.");
            } catch (FileNotFoundException ex) {
                System.out.println("Unable to find file '" + args[0] + "'.");
            }
        } else {
            System.out.println("Usage: filename.txt");
        }
    }
}
