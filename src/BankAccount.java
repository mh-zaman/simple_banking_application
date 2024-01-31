import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BankAccount {
    private String name;
    private int accountNumber;
    private String creationDate;
    protected double balance;
    private double minimumBalance;

    public BankAccount(String name, int accountNumber, double minimumBalance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.minimumBalance = minimumBalance;
        this.creationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.balance = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public double getBalance() {
        return balance;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public abstract String getAccountType();


    public abstract void deposit(double amount) throws BankingException;

    public abstract void withdraw(double amount) throws BankingException;

    public String toString() {
        return "Account Type: " + getAccountType() +
                "\nAccount Number: " + accountNumber +
                "\nName: " + name +
                "\nCreation Date: " + creationDate +
                "\nBalance: $" + balance +
                "\nMinimum Balance: $" + minimumBalance;
    }
}
