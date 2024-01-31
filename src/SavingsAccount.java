public class SavingsAccount extends BankAccount{
    private double interestRate;

    public SavingsAccount(String name, int accountNumber, double minimumBalance, double interestRate) {
        super(name, accountNumber, minimumBalance);
        this.interestRate = interestRate;
    }

    public void deposit(double amount) throws BankingException {
        if (amount < 0) {
            throw new BankingException("Deposit amount must be positive.");
        }

        balance += amount + (amount * interestRate);
        System.out.println("Amount deposited successfully. New balance: €" + balance);
    }

    public void withdraw(double amount) throws BankingException {
        if (amount < 0) {
            throw new BankingException("Withdrawal amount must be positive.");
        }

        if (balance - amount >= getMinimumBalance()) {
            balance -= amount;
            System.out.println("Amount withdrawn successfully. New balance: € " + balance);
        } else {
            throw new BankingException("Insufficient funds. Withdrawal not allowed.");
        }
    }

    public String getAccountType() {
        return "Savings Account";
    }
}