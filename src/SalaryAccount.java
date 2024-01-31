public class SalaryAccount extends BankAccount {
    private double monthlySalary;

    public SalaryAccount(String name, int accountNumber, double minimumBalance, double monthlySalary) {
        super(name, accountNumber, minimumBalance);
        this.monthlySalary = monthlySalary;
    }

    public void deposit(double amount) throws BankingException {
        if (amount < 0) {
            throw new BankingException("Deposit amount must be positive.");
        }
        balance += amount;
        System.out.println("Deposit successful. New balance: €" + balance);

        if (isBonusDeposit(amount)) {
            System.out.println("Bonus detected! Amount: €" + amount);
            double bonusAmount = amount - monthlySalary;
            processBonus(bonusAmount);
        }
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

    private boolean isBonusDeposit(double depositAmount) {

        return depositAmount > monthlySalary;
    }

    private void processBonus(double bonusAmount) {
        System.out.println("Bonus processed! Bonus Amount: €" + bonusAmount);
    }

    public String getAccountType() {
        return "Salary Account";
    }
}
