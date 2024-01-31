import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SavingsAccount extends BankAccount {
    private double interestRate;
    private LocalDate lastInterestDate;

    public SavingsAccount(String name, int accountNumber, double minimumBalance, double interestRate) {
        super(name, accountNumber, minimumBalance);
        this.interestRate = interestRate;
        this.lastInterestDate = LocalDate.now();
    }

    public void deposit(double amount) throws BankingException {
        if (amount < 0) {
            throw new BankingException("Deposit amount must be positive.");
        }

        applyMonthlyInterest();

        balance += amount;

        System.out.println("Amount deposited successfully. New balance: €" + balance);
    }

    public void withdraw(double amount) throws BankingException {
        if (amount < 0) {
            throw new BankingException("Withdrawal amount must be positive.");
        }

        applyMonthlyInterest();

        if (balance - amount >= getMinimumBalance()) {
            balance -= amount;
            System.out.println("Amount withdrawn successfully. New balance: € " + balance);
        } else {
            throw new BankingException("Insufficient funds. Withdrawal not allowed.");
        }
    }

    private void applyMonthlyInterest() {
        LocalDate currentDate = LocalDate.now();

        long monthsBetween = ChronoUnit.MONTHS.between(lastInterestDate, currentDate);

        if (monthsBetween > 0) {
            for (int i = 0; i < monthsBetween; i++) {
                double monthlyInterest = balance * (interestRate / 100);
                balance += monthlyInterest;
            }

            System.out.println("Monthly interest applied. New balance: € " + balance);

            lastInterestDate = currentDate;
        }
    }

    public String getAccountType() {
        return "Savings Account";
    }
}