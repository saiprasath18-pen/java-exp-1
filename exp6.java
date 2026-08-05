import java.util.Scanner;

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

interface BankAccount {
    void deposit(double amount);
    void withdraw(double amount) throws InsufficientBalanceException;
    double getBalance();
}

class CustomerAccount implements BankAccount {
    private String accountHolderName;
    private int accountNumber;
    private double balance;

    public CustomerAccount(String accountHolderName, int accountNumber, double balance) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Amount Deposited Successfully");
        System.out.println("Available Balance: Rs. " + (long) balance);
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient Balance");
        }
        balance -= amount;
        System.out.println("Amount Withdrawn Successfully");
        System.out.println("Available Balance: Rs. " + (long) balance);
    }

    public double getBalance() {
        return balance;
    }
}

public class BankOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
	
        System.out.print("Enter Account Holder Name:");
        String name = scanner.nextLine();

        System.out.print("Enter Account Number: ");
        int accountNumber = scanner.nextInt();

        System.out.print("Enter Initial Balance: ");
        double initialBalance = scanner.nextDouble();

        scanner.nextLine();

        System.out.print("Enter Operation (Deposit/Withdraw): ");
        String operation = scanner.nextLine();

        BankAccount account = new CustomerAccount(name, accountNumber, initialBalance);


        try {
            if (operation.equalsIgnoreCase("Deposit")) {
                System.out.print("Enter Deposit Amount: ");
                double depositAmount = scanner.nextDouble();
                account.deposit(depositAmount);
            } else if (operation.equalsIgnoreCase("Withdraw")) {
                System.out.print("Enter Withdraw Amount: ");
                double withdrawAmount = scanner.nextDouble();
                account.withdraw(withdrawAmount);
            } else {
                System.out.println("Invalid Operation!");
            }
        } 
        catch (InsufficientBalanceException e) {
            System.out.println("Exception: " + e.getMessage());
            System.out.println("Transaction Failed");
            System.out.println("Available Balance: Rs. " + (long) account.getBalance());
        } 
        catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } 
        finally {
            scanner.close();
        }
    }
}
