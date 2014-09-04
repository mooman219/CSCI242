
/**
 * @author Joseph Cumbo
 */
public class PizzaRun {

    /**
     * number of slices per whole pizza
     */
    public static final int SLICE_PER_PIE = 8;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int v;
        int j = v;
        if (args.length > 0) {
            double price = 0;
            try {
                price = Double.parseDouble(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid price entry on '" + args[0] + "'");
                return;
            }
            int totalSlices = 0;
            if (args.length > 1) {
                for (int i = 1; i < args.length; i++) {
                    try {
                        totalSlices += Integer.parseInt(args[i]);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid slice entry on '" + args[i] + "'");
                        return;
                    }
                }
            }
            int pies = calcWholePies(totalSlices);
            System.out.println("Buy " + pies + " pizzas for $" + (price * pies));
            System.out.println("There will be " + ((SLICE_PER_PIE * pies) - totalSlices) + " extra slices.");
        } else {
            System.out.println("Error: Please enter a price.");
        }
    }

    /**
     * calcWholePies returns the number of whole pizza pies are needed to fill
     * the order for a given number of slices.
     *
     * @param nSlices - number of slices desired
     * @return number of whole pizza pies
     */
    public static int calcWholePies(int nSlices) {
        return nSlices == 0 ? 0 : (nSlices / SLICE_PER_PIE) + 1;
    }
}
