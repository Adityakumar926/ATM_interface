import java.util.Scanner;
import java.util.ArrayList;

class BankAccount {
    private String accountNumber;
    private double balance;
    private String pin;

    public BankAccount(String accountNumber, double initialBalance, String pin) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.pin = pin;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. Current balance: ₹" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. Current balance: ₹" + balance);
            return true;
        } else {
            System.out.println("Insufficient funds or invalid withdrawal amount.");
            return false;
        }
    }

    public double getBalance() {
        return balance;
    }

    public boolean checkPin(String inputPin) {
        return this.pin.equals(inputPin);
    }
}

public class ATM {
    private ArrayList<BankAccount> accounts = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Create Account");
        System.out.println("2. Login to Account");
        System.out.println("3. Exit");
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Thank you for using the ATM.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void createAccount() {
        System.out.print("Enter a new account number: ");
        String accountNumber = scanner.nextLine();
        if (findAccount(accountNumber) != null) {
            System.out.println("Account number already exists.");
            return;
        }
        System.out.print("Set a PIN for your account: ");
        String pin = scanner.nextLine();
        accounts.add(new BankAccount(accountNumber, 0.0, pin));
        System.out.println("Account created successfully.");
    }

    private void login() {
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = findAccount(accountNumber);
        if (account == null) {
            System.out.println("Account number does not exist.");
            return;
        }
        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine();
        if (account.checkPin(pin)) {
            manageAccount(account);
        } else {
            System.out.println("Incorrect PIN.");
        }
    }

    private BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    private void manageAccount(BankAccount account) {
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Current balance: ₹" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    exit = true;
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}