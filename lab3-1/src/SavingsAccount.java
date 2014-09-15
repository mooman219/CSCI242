
/**
 * @author Joseph Cumbo
 */
public class SavingsAccount extends BankAccount {

    public static double SAVINGS_MONTHLY_INTEREST_RATE = 0.03;

    public SavingsAccount(double newMoney, String ownerName) {
        super(newMoney, ownerName);
    }

    @Override
    public void calcInterest() {
        addInterest(getCurrentBalance() * SAVINGS_MONTHLY_INTEREST_RATE);
    }

    @Override
    public String toString() {
        return "Account Type: Savings " + super.toString();
    }

}
