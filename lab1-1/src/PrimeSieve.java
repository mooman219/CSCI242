
/**
 * PrimeSieve.java
 *
 * Version: $Id$
 *
 * Revisions: $Log$
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * A class that computes prime numbers between 2 and N, using the Sieve of
 * Eratosthenes algorithm.
 * <P>
 * Example 1: On the second line, the program waits after the colon character
 * for the user to enter the maximum number, N, and the user enters 72 as the
 * value for 'N'.
 * <PRE>
 * $ java PrimeSieve
 * Compute prime numbers from 2 to: 72
 * Prime numbers: 2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71
 * $
 * </PRE>
 * <P>
 * Example 2: The user enters 1, an invalid value for 'N', and the program
 * displays the following message instead of running the algorithm:
 * <PRE>
 * $ java PrimeSieve
 * Compute prime numbers from 2 to: 1
 * N must be greater than or equal to 2.
 * </PRE>
 *
 * @author sps Sean Strout, Joseph Cumbo
 */
public class PrimeSieve {

    /**
     * Find and return the prime numbers between 2 and N inclusive.
     *
     * @param N The maximum value (inclusive) to check.
     * @return A list of prime numbers
     */
    public static ArrayList<Integer> findPrimes(int N) {
        ArrayList<Integer> primes = new ArrayList<Integer>();
        for (int i = 2; i <= N; i++) {
            primes.add(i);
        }
        for (int i = 2; i <= N; i++) {
            for (Iterator<Integer> iterator = primes.iterator();
                    iterator.hasNext();) {
                Integer current = iterator.next();
                Double value = current.doubleValue() / i;
                if (Math.floor(value) == value && value != 1) {
                    iterator.remove();
                }
            }
        }
        return primes;
    }   // findPrimes()

    /**
     * The main method.
     *
     * @param args The command line arguments (unused)
     */
    public static void main(String[] args) {

        // read input value, N
        System.out.print("Compute prime numbers from 2 to: ");
        Scanner in = new Scanner(System.in);
        int N = 0;
        try {
            N = in.nextInt();
        } catch (Exception e) {
            System.out.println("N must be a valid integer.");
            return;
        }
        if (N < 2) {
            System.out.println("N must be greater than or equal to 2.");
        } else {
            ArrayList<Integer> primes = findPrimes(N);

            // display output
            System.out.print("Prime numbers: ");
            for (Integer i : primes) {
                System.out.print(i + " ");
            }
        }
    }   // main()
}   // PrimeSieve{}
