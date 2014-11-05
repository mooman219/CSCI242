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
        if (args.length > 0) {
            try {
                HashTable simpleTable = new HashTable(Hash.Htype.SIMPLE, 100000);
                HashTable customTable = new HashTable(Hash.Htype.CUSTOM, 100000);
                Scanner scanner = new Scanner(new FileReader(args[0]));
                while (scanner.hasNext()) {
                    String word = scanner.next();
                    simpleTable.put(word);
                    customTable.put(word);
                }
                System.out.println(simpleTable.imbalance());
                System.out.println(customTable.imbalance());
            } catch (FileNotFoundException ex) {
                System.out.println("Unable to find file '" + args[0] + "'.");
            }
        } else {
            System.out.println("Usage: filename.txt");
        }
    }
}
