
/**
 * @author Joseph Cumbo
 */
public class CDAccount extends BankAccount {

    /**
     * The minimum amount that must be kept in the account at all times;
     * interest is earned only on the amount over the minimum - currently $1000.
     */
    public static double MINIMUM_BALANCE = 1000;
    /**
     * The interest rate for balances greater than the minimum - currently 6%.
     */
    public static double MONTHLY_INTEREST_RATE = 0.06;

    /**
     * A constructor for a CDAccount object. It accepts the amount of money
     * deposited (the balance) when the account is created.
     *
     * @param newMoney The amount of money deposited when the account is opened
     * @param ownerName The owner of this account
     */
    public CDAccount(double newMoney, String ownerName) {
        super(newMoney, ownerName);
    }

    /**
     * Calculate interest for this account. The rules for earning interest are
     * different for every kind of account.
     */
    @Override
    public void calcInterest() {
        double growableBalance = getCurrentBalance() - MINIMUM_BALANCE;
        if (growableBalance > 0) {
            addInterest(growableBalance * MONTHLY_INTEREST_RATE);
        }
    }

    @Override
    public String toString() {
        return "Account Type: CD " + super.toString();
    }
}
