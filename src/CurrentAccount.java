public class CurrentAccount extends BankAccount {
    private double overdraftLimit;

    public CurrentAccount(String name, int accountNumber, double minimumBalance, double overdraftLimit) {
        super(name, accountNumber, minimumBalance);
        this.overdraftLimit = overdraftLimit;
    }

    public void deposit(double amount) throws BankingException {
        if (amount < 0) {
            throw new BankingException("Deposit amount must be positive.");
        }

        balance += amount;
        System.out.println("Amount deposited successfully. New balance: € " + balance);
        System.out.println(super.toString());
    }

    public void withdraw(double amount) throws BankingException {
        if (amount < 0) {
            throw new BankingException("Withdrawal amount must be positive.");
        }

        if (balance - amount >= -overdraftLimit) {
            balance -= amount;
            System.out.println("Amount withdrawn successfully. New balance: € " + balance);
        } else {
            throw new BankingException("Insufficient funds. Withdrawal not allowed.");
        }
    }

    public String getAccountType() {
        return "Current Account";
    }
}