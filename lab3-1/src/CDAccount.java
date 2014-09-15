
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
    public static double MONTHLY_INTEREST_RATE = 0.06 / 12;

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
     * Calculate interest on this account. With C of D accounts no interest is
     * earned on the minimum that must always be kept in the account. Interest
     * is only earned on everything over that amount. Interest earned should be
     * added to the current balance.
     */
    @Override
    public void calcInterest() {
        double growableBalance = getCurrentBalance() - MINIMUM_BALANCE;
        if (growableBalance > 0) {
            addInterest(growableBalance * MONTHLY_INTEREST_RATE);
        }
    }

    /**
     * A printable version of this account.
     *
     * @return The account type, owner name, and the current balance.
     */
    @Override
    public String toString() {
        return "Account Type: CD " + super.toString();
    }
}
