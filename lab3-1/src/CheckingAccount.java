
/**
 * @author Joseph Cumbo
 */
public class CheckingAccount extends BankAccount {

    public static double BONUS_MONTHLY_RATE = 0.015;
    private boolean bonus;

    public CheckingAccount(double newMoney, String ownerName, boolean bonus) {
        super(newMoney, ownerName);
        this.bonus = bonus;
    }

    @Override
    public void calcInterest() {
        if (bonus) {
            addInterest(getCurrentBalance() * BONUS_MONTHLY_RATE);
        }
    }

    @Override
    public String toString() {
        return "Account Type: Checking " + super.toString();
    }

}
