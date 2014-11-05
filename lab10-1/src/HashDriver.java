import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author Joseph Cumbo (mooman219)
 */
public class HashDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Buiding table for: " + args[0]);
        if (args.length > 0) {
            HashTable simpleTable = new HashTable(Hash.Htype.SIMPLE, 100000);
            HashTable customTable = new HashTable(Hash.Htype.CUSTOM, 100000);
            try (Scanner scanner = new Scanner(new FileReader(args[0]));) {
                int words = 0;
                while (scanner.hasNext()) {
                    String word = scanner.next();
                    simpleTable.put(word);
                    customTable.put(word);
                    words++;
                }
                System.out.println("Total words: " + words);
                System.out.println("Simple hash imbalance: " + simpleTable.imbalance());
                System.out.println("Custom hash imbalance: " + customTable.imbalance());
            } catch (FileNotFoundException ex) {
                System.out.println("Unable to find file '" + args[0] + "'.");
            }
        } else {
            System.out.println("Usage: filename.txt");
        }
    }
}
