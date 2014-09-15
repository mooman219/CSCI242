
/**
 * @author Joseph Cumbo
 */
public class CDAccount extends BankAccount {

    public static double MINIMUM_BALANCE = 1000;
    public static double MONTHLY_INTEREST_RATE = 0.06;

    public CDAccount(double newMoney, String ownerName) {
        super(newMoney, ownerName);
    }

    @Override
    public void calcInterest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) {

    }

    @Override
    public String toString() {
        return "Account Type: CD " + super.toString();
    }

}
