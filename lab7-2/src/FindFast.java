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
 * Finds the best location quickly.
 *
 * @author Joseph Cumbo
 */
public class FindFast {

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
            return;
        }
        //
        // Sort the data.
        //
        FindFast find = new FindFast();
        Collections.shuffle(places);
        long time = 0;
        time = System.currentTimeMillis();
        places = find.sort(places, places.size() / 2);
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

    /**
     * Creates a shallow copy of the given list and sorts it up to the kth
     * smallest element.
     *
     * @param list the list to be sorted.
     * @param k the kth smallest element to sort up to.
     * @return a new sorted list.
     */
    public <T extends Comparable<T>> List<T> sort(List<T> list, int k) {
        Comparable[] array = list.toArray(new Comparable[list.size()]);
        quickselect(array, k);
        return Arrays.asList((T[]) array);
    }

    /**
     * Sorts the given array up to the kth smallest element.
     *
     * @param array the array to be sorted.
     * @param k the kth smallest element to sort up to.
     * @return the element at index k.
     */
    private Comparable quickselect(Comparable[] array, int k) {
        return quickselect(array, 0, array.length - 1, k - 1);
    }

    private Comparable quickselect(Comparable[] array, int first, int last, int k) {
        if (first <= last) {
            int pivot = partition(array, first, last);
            if (pivot == k) {
                return array[k];
            }
            if (pivot > k) {
                return quickselect(array, first, pivot - 1, k);
            }
            return quickselect(array, pivot + 1, last, k);
        }
        return Integer.MIN_VALUE;
    }

    private int partition(Comparable[] array, int first, int last) {
        int pivot = first;
        swap(array, last, pivot);
        for (int i = first; i < last; i++) {
            if (array[i].compareTo(array[last]) > 0) {
                swap(array, i, first);
                first++;
            }
        }
        swap(array, first, last);
        return first;
    }

    /**
     * Swaps two elements in the array.
     *
     * @param array array where swap takes place.
     * @param x index of first element to swap.
     * @param y index of second element to swap.
     */
    private void swap(Object[] array, int x, int y) {
        Object tmp = array[x];
        array[x] = array[y];
        array[y] = tmp;
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
