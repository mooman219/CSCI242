
/**
 * @author Joseph Cumbo
 */
public abstract class BankAccount {

    private String ownerName;
    private double interestEarned = 0;
    private double currentBalance;

    /**
     * A constructor for a BankAccount object. It accepts the amount of money
     * deposited (the balance) when the account is created.
     *
     * @param newMoney The amount of money deposited when the account is opened
     * @param ownerName The owner of this account
     */
    public BankAccount(double newMoney, String ownerName) {
        this.ownerName = ownerName;
        this.currentBalance = newMoney;
    }

    /**
     * Calculate interest for this account. The rules for earning interest are
     * different for every kind of account.
     */
    public abstract void calcInterest();

    /**
     * Store the interest earned.
     *
     * @param interest The calculated interest amount
     */
    protected void setInterestEarned(double interest) {
        this.interestEarned = interest;
    }

    /**
     * Print a statement for this month.
     */
    public void printStatement() {
        System.out.printf("%-20s "
                + "Interest Earned: %10.2f "
                + "Current Balance: %15.2f\n", ownerName, interestEarned, currentBalance);
    }

    /**
     * Return the current balance.
     *
     * @return The current balance
     */
    public double getCurrentBalance() {
        return currentBalance;
    }

    /**
     * Return the current interest earned.
     *
     * @return The current interest earned
     */
    public double getInterest() {
        return interestEarned;
    }

    /**
     * Add the current interest amount to the current balance.
     *
     * @param newInterestEarned The total amount of interest earned.
     */
    protected void addInterest(double newInterestEarned) {
        this.interestEarned += newInterestEarned;
        this.currentBalance += newInterestEarned;
    }

    /**
     * A printable version of this account.
     *
     * @return The owner name and the current balance.
     */
    @Override
    public String toString() {
        return "Owner Name: " + ownerName
                + " Current Balance: " + currentBalance;
    }
}
