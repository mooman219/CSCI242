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
            return;
        }

        FindSlow find = new FindSlow();
        Collections.shuffle(places);
        for (Business business : find.sort(places)) {
            System.out.println("Business: " + business.getName() + " Distance: " + business.getDistance());
        }
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
     * @author Joseph Cumbo
     */
    public static class Business implements Comparable<Business> {

        private final String businessName;
        private final int distance;

        public Business(String businessName, int distance) {
            this.businessName = businessName;
            this.distance = distance;
        }

        public String getName() {
            return businessName;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public int compareTo(Business o) {
            return distance < o.distance
                    ? -1 : distance == o.distance
                            ? 0 : 1;
        }
    }
}
