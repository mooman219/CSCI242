
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joseph Cumbo
 */
public class TestBankAccount {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<BankAccount> accounts = new ArrayList<BankAccount>();
        accounts.add(new CDAccount(11150, "John Anderson"));
        accounts.add(new CheckingAccount(64665.75, "Sharon Smith", false));
        accounts.add(new CDAccount(10000, "Phil Phillips"));
        accounts.add(new SavingsAccount(5000, "Bill Jones"));

        calculateInterest(accounts);
        generateReport(accounts);
    }

    public static void calculateInterest(List<BankAccount> accounts) {
        for (BankAccount account : accounts) {
            account.calcInterest();
        }
    }

    public static void generateReport(List<BankAccount> accounts) {
        System.out.println("Interest Report\n");
        double totalInterest = 0;
        double totalBalance = 0;
        for (BankAccount account : accounts) {
            account.printStatement();
            totalInterest += account.getInterest();
            totalBalance += account.getCurrentBalance();
        }
        System.out.println("\nTotal interest: " + totalInterest);
        System.out.println("Total balance: " + totalBalance);
    }
}
