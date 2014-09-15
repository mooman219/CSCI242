
/**
 * @author Joseph Cumbo
 */
public abstract class BankAccount {

    private String ownerName;
    private double interestEarned = 0;
    private double currentBalance;

    public BankAccount(double newMoney, String ownerName) {
        this.ownerName = ownerName;
        this.currentBalance = newMoney;
    }

    public abstract void calcInterest();

    protected void setInterestEarned(double interest) {
        this.interestEarned = interest;
    }

    public void printStatement() {
        System.out.printf("%-20s "
                + "Interest Earned: %10.2f "
                + "Current Balance: %15.2f\n", ownerName, interestEarned, currentBalance);
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public double getInterest() {
        return interestEarned;
    }

    protected void addInterest(double newInterestEarned) {
        this.interestEarned += newInterestEarned;
        this.currentBalance += newInterestEarned;
    }

    @Override
    public String toString() {
        return "Owner Name: " + ownerName
                + " Interest Earned: " + interestEarned
                + " Current Balance: " + currentBalance;
    }
}
