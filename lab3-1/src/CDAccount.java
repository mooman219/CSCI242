
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
        double growableBalance = this.getCurrentBalance() - MINIMUM_BALANCE;
        if (growableBalance > 0) {
            this.addInterest(growableBalance * MONTHLY_INTEREST_RATE);
        }
    }

    public static void main(String[] args) {

    }

    @Override
    public String toString() {
        return "Account Type: CD " + super.toString();
    }

}
