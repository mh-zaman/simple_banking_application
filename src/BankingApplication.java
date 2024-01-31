import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingApplication {

    private static ArrayList<BankAccount> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            try {
                System.out.println("\nActions : ");
                System.out.println("1. Create a new Savings account");
                System.out.println("2. Create a new Current account");
                System.out.println("3. Create a new Salary account");
                System.out.println("4. Display all accounts");
                System.out.println("5. Update an account");
                System.out.println("6. Delete an account");
                System.out.println("7. Deposit an amount into your account");
                System.out.println("8. Withdraw an amount from your account");
                System.out.println("9. Search for account");
                System.out.println("10. Exit");

                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        createSavingsAccount();
                        break;

                    case 2:
                        createCurrentAccount();
                        break;

                    case 3:
                        createSalaryAccount();
                        break;

                    case 4:
                        displayAccounts();
                        break;

                    case 5:
                        updateAcount();
                        break;

                    case 6:
                        deleteAcount();
                        break;

                    case 7:
                        deposit();
                        break;

                    case 8:
                        withdraw();
                        break;

                    case 9:
                        searchAccount();
                        break;

                    case 10:
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a number between 1-10");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number");
                scanner.nextLine();
                choice = -1;
            } catch (Exception e) {
                System.out.println("An unexpected error occurred. Please try again");
                scanner.nextLine();
                choice = -1;
            }
        } while (choice != 10);
    }

    private static void createSavingsAccount() {
        try {
            System.out.print("Enter account holder name: ");
            scanner.nextLine();
            String name = scanner.nextLine();

            double initialDeposit;
            System.out.print("Enter initial deposit amount (€): ");
            initialDeposit = scanner.nextDouble();

            double minimumBalance = 100;
            System.out.println("Minimum balance requirement for a Savings Account: € " + minimumBalance);

            System.out.print("Enter interest rate for the savings account: ");
            double interestRate = scanner.nextDouble();

            int accountNumber = generateAccountNumber();

            if (initialDeposit >= minimumBalance) {
                SavingsAccount newAccount = new SavingsAccount(name, accountNumber, minimumBalance, interestRate);
                newAccount.deposit(initialDeposit);
                accounts.add(newAccount);

                System.out.println("Savings Account created successfully. Account number: " + accountNumber);
            } else {
                throw new BankingException("Insufficient initial deposit to meet the minimum balance requirement");
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid numeric values");
            scanner.nextLine();
        } catch (BankingException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred. Please try again");
            scanner.nextLine();
        }
    }

    private static void createCurrentAccount() {
        try {
            System.out.print("Enter account holder name: ");
            scanner.nextLine();
            String name = scanner.nextLine();

            double initialDeposit;
            System.out.print("Enter initial deposit amount (€): ");
            initialDeposit = scanner.nextDouble();

            double minimumBalance = 500;
            System.out.println("Minimum balance requirement for a Current Account: € " + minimumBalance);

            System.out.print("Enter overdraft limit for the current account: € ");
            double overdraftLimit = scanner.nextDouble();

            int accountNumber = generateAccountNumber();

            if (initialDeposit >= minimumBalance) {
                CurrentAccount newAccount = new CurrentAccount(name, accountNumber, minimumBalance, overdraftLimit);
                newAccount.deposit(initialDeposit);
                accounts.add(newAccount);

                System.out.println("Current Account created successfully. Account number: " + accountNumber);
            } else {
                throw new BankingException("Insufficient initial deposit to meet the minimum balance requirement");
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid numeric values");
            scanner.nextLine();
        } catch (BankingException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred. Please try again");
            scanner.nextLine();
        }
    }

    private static void createSalaryAccount() {
        try {
            System.out.print("Enter account holder name: ");
            scanner.nextLine();
            String name = scanner.nextLine();

            System.out.print("Enter initial deposit amount (€): ");
            double initialDeposit = scanner.nextDouble();

            double minimumBalance = 0;
            System.out.println("Minimum balance requirement for a Salary Account: € " + minimumBalance);

            System.out.print("Enter monthly salary for the salary account: € ");
            double monthlySalary = scanner.nextDouble();

            int accountNumber = generateAccountNumber();

            if (initialDeposit >= minimumBalance) {
                SalaryAccount newAccount = new SalaryAccount(name, accountNumber, minimumBalance, monthlySalary);
                newAccount.deposit(initialDeposit);
                accounts.add(newAccount);

                System.out.println("Salary Account created successfully. Account number: " + accountNumber);
            } else {
                throw new BankingException("Insufficient initial deposit to meet the minimum balance requirement");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid numeric values");
            scanner.nextLine();
        } catch (BankingException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred. Please try again");
            scanner.nextLine();
        }
    }

    private static void displayAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts to display.");
        } else {
            for (BankAccount account : accounts) {
                System.out.println("\n" + account);
            }
        }
    }

    private static void updateAcount() {
        try {
            System.out.print("Enter account number to update: ");
            int accountNumber = scanner.nextInt();

            BankAccount account = findAccount(accountNumber);
            if (account != null) {
                System.out.println("\nCurrent details:\n" + account);

                System.out.println("\nSelect the information to update:");
                System.out.println("1. Account Holder's Name");
                System.out.println("2. Minimum Balance");
                System.out.println("3. Cancel");

                int updateChoice = scanner.nextInt();
                scanner.nextLine();

                switch (updateChoice) {
                    case 1:
                        System.out.print("Enter new account holder name: ");
                        String newName = scanner.nextLine();
                        account.setName(newName);
                        System.out.println("Account holder's name updated successfully");
                        break;

                    case 2:
                        System.out.print("Enter new minimum balance (€): ");
                        double newMinBalance = scanner.nextDouble();
                        account.setMinimumBalance(newMinBalance);
                        System.out.println("Minimum balance updated successfully");
                        break;

                    case 3:
                        System.out.println("Update canceled");
                        break;

                    default:
                        System.out.println("Invalid choice. Update canceled");
                }

                System.out.println("Updated details:\n" + account);
            } else {
                System.out.println("Account not found with the given account number");
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid account number");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred. Please try again");
            scanner.nextLine();
        }
    }

    private static void deleteAcount() {
        try {
            System.out.print("Enter account number to delete: ");
            int accountNumber = scanner.nextInt();

            BankAccount account = findAccount(accountNumber);
            if (account != null) {
                accounts.remove(account);
                System.out.println("Account deleted successfully");
            } else {
                System.out.println("Account not found with the given account number");
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid account number");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred. Please try again");
            scanner.nextLine();
        }
    }

    private static void deposit() {

    }

    private static void withdraw() {

    }

    private static void searchAccount() {

    }

    public static int generateAccountNumber() {
        return (int) (Math.random() * 90000) + 10000;
    }

    private static BankAccount findAccount(int accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

}