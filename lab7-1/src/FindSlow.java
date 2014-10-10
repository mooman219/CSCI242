import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Joseph Cumbo
 */
public class FindSlow {

    private static Scanner scanner = new Scanner(System.in);

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
        // Read the file
        //
        ArrayList<Business> places = new ArrayList<Business>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] info = line.split(" ");
                if (info.length < 2) { // Omit invalid lines
                    continue;
                }
                try {
                    places.add(new Business(info[0], Integer.parseInt(info[1])));
                } catch (NumberFormatException ex) { // Omit lines with invalid distances.
                    continue;
                }
            }
        } catch (IOException ex) {
            System.out.println("Unable to read file.");
        }
    }

    public static List<Comparable> mergeSort(List<Comparable> list) {

        return null;
    }

    /**
     * @author Joseph Cumbo
     */
    public static class Business implements Comparable<Business> {

        private final String businessName;
        private final int distance;

        public Business(String businessName, int distance) {
            this.businessName = businessName;
            this.distance = distance;
        }

        public String getBusinessName() {
            return businessName;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public int compareTo(Business o) {
            return distance < o.distance
                    ? 1 : distance == o.distance
                            ? 0 : -1;
        }
    }
}
