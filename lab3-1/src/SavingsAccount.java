
/**
 * @author Joseph Cumbo
 */
public class SavingsAccount extends BankAccount {

    /**
     * The annual interest rate for a standard savings account is currently 3%.
     */
    public static double SAVINGS_MONTHLY_INTEREST_RATE = 0.03;

    /**
     * A constructor for a Savings object. It accepts the amount of money
     * deposited (the balance) when the account is created.
     *
     * @param newMoney The amount of money deposited when the account is opened
     * @param ownerName The owner of this account
     */
    public SavingsAccount(double newMoney, String ownerName) {
        super(newMoney, ownerName);
    }

    /**
     * Calculate interest for this account. The rules for earning interest are
     * different for every kind of account.
     */
    @Override
    public void calcInterest() {
        addInterest(getCurrentBalance() * SAVINGS_MONTHLY_INTEREST_RATE);
    }

    /**
     * A printable version of this account.
     *
     * @return The account type, owner name, and the current balance.
     */
    @Override
    public String toString() {
        return "Account Type: Savings " + super.toString();
    }
}
