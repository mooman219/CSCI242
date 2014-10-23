import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Finds the best location slowly.
 *
 * @author Joseph Cumbo
 */
public class FindSlow {

    // Scanner used for taking user input.
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
        List<Business> places = new ArrayList<Business>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
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
            return;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    System.out.println("Unable to close file reader.");
                }
            }
        }
        //
        // Sort the data.
        //
        FindSlow find = new FindSlow();
        Collections.shuffle(places);
        long time = 0;
        time = System.currentTimeMillis();
        places = find.sort(places);
        time = System.currentTimeMillis() - time;
        System.out.println("The sort took " + time + "ms.");
        //
        // Analyze Strategies
        //
        System.out.println("Midpoint: " + places.get(places.size() / 2).getDistance());
        if (places.size() % 2 == 1) { // If odd use the middle element.
            System.out.println("Median: "
                    + places.get(places.size() / 2).getDistance());
        } else { // If even then average the middle elements
            double median = (places.get(places.size() / 2).getDistance()
                    + places.get((places.size() / 2) - 1).getDistance()) / 2d;
            System.out.println("Median: " + median);
        }
        double totalSize = 0;
        for (Business business : places) {
            totalSize += business.getDistance();
        }
        System.out.println("Average: " + totalSize / places.size());
    }

    public <T extends Comparable<T>> List<T> sort(List<T> list) {
        Comparable[] array = list.toArray(new Comparable[list.size()]);
        mergeSort(array, new Comparable[array.length], 0, array.length - 1);
        return Arrays.asList((T[]) array);
    }

    private void mergeSort(Comparable[] array, Comparable[] t, int s, int e) {
        if (s < e) {
            int m = s + (e - s) / 2;
            mergeSort(array, t, s, m);
            mergeSort(array, t, m + 1, e);
            merge(array, t, s, m, e);
        }
    }

    private void merge(Comparable[] array, Comparable[] t, int s, int m, int e) {
        int index = s;
        int lp = s;
        int rp = m + 1;
        for (int i = s; i <= e; i++) {
            t[i] = array[i];
        }
        while (lp <= m && rp <= e) {
            if (t[lp].compareTo(t[rp]) <= 0) {
                array[index] = t[lp];
                lp++;
            } else {
                array[index] = t[rp];
                rp++;
            }
            index++;
        }
        while (lp <= m) {
            array[index] = t[lp];
            lp++;
            index++;
        }
    }

    /**
     * Stores the information for a business.
     *
     * @author Joseph Cumbo
     */
    public static class Business implements Comparable<Business> {

        // Name of the business
        private final String businessName;
        // Distance of the business
        private final int distance;

        /**
         * Creates a business instance with given name and distance.
         *
         * @param businessName the name of the business.
         * @param distance the distance of the business.
         */
        public Business(String businessName, int distance) {
            this.businessName = businessName;
            this.distance = distance;
        }

        /**
         * Gets the businesses name.
         *
         * @return the name of the business.
         */
        public String getName() {
            return businessName;
        }

        /**
         * Gets the distance of the business.
         *
         * @return the distance.
         */
        public int getDistance() {
            return distance;
        }

        /**
         * Compares this business to another business based on the distance.
         *
         * @param o the other business to compare to.
         * @return 1 if this business's distance if larger than the given, 0 if
         * the distances are equal, and -1 otherwise.
         */
        @Override
        public int compareTo(Business o) {
            return distance < o.distance
                    ? 1 : distance == o.distance
                            ? 0 : -1;
        }
    }
}
