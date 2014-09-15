
/**
 * @author Joseph Cumbo
 */
public class CheckingAccount extends BankAccount {

    /**
     * The interest rate for a bonus account - currently 1.5% per year.
     */
    public static double BONUS_MONTHLY_RATE = 0.015;
    private boolean bonus;

    /**
     * A constructor for a CheckingAccount object. It accepts the amount of
     * money deposited (the balance) when the account is created.
     *
     * @param newMoney The amount of money deposited when the account is opened
     * @param ownerName The owner of this account
     */
    public CheckingAccount(double newMoney, String ownerName, boolean bonus) {
        super(newMoney, ownerName);
        this.bonus = bonus;
    }

    /**
     * Calculate interest for this account. The rules for earning interest are
     * different for every kind of account.
     */
    @Override
    public void calcInterest() {
        if (bonus) {
            addInterest(getCurrentBalance() * BONUS_MONTHLY_RATE);
        }
    }

    /**
     * A printable version of this account.
     *
     * @return The account type, owner name, and the current balance.
     */
    @Override
    public String toString() {
        return "Account Type: Checking " + super.toString();
    }

}
