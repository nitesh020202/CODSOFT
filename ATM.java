package nitesh.codsoft;
import java.util.Scanner;

//BankAccount class representing the user's bank account
class BankAccount {
 private double balance;

 public BankAccount(double initialBalance) {
     balance = initialBalance;
 }

 public double getBalance() {
     return balance;
 }

 public void deposit(double amount) {
     balance += amount;
 }

 public boolean withdraw(double amount) {
     if (balance >= amount) {
         balance -= amount;
         return true; // Withdrawal successful
     } else {
         return false; // Insufficient funds
     }
 }
}

//ATM class representing the ATM machine
public class ATM {
 private BankAccount account;

 public ATM(BankAccount account) {
     this.account = account;
 }

 public void displayOptions() {
     System.out.println("Welcome to the ATM!");
     System.out.println("1. Withdraw");
     System.out.println("2. Deposit");
     System.out.println("3. Check Balance");
     System.out.println("4. Exit");
 }

 public void withdraw(double amount) {
     boolean success = account.withdraw(amount);
     if (success) {
         System.out.println("Withdrawal successful. Remaining balance: " + account.getBalance());
     } else {
         System.out.println("Insufficient funds. Withdrawal failed.");
     }
 }

 public void deposit(double amount) {
     account.deposit(amount);
     System.out.println("Deposit successful. New balance: " + account.getBalance());
 }

 public void checkBalance() {
     System.out.println("Your current balance is: " + account.getBalance());
 }

 public static void main(String[] args) {
     BankAccount userAccount = new BankAccount(1000); // Initialize account with a balance of 1000
     ATM atm = new ATM(userAccount);
     Scanner scanner = new Scanner(System.in);
     boolean exit = false;

     while (!exit) {
         atm.displayOptions();
         System.out.print("Choose an option: ");
         int option = scanner.nextInt();

         switch (option) {
             case 1:
                 System.out.print("Enter withdrawal amount: ");
                 double withdrawAmount = scanner.nextDouble();
                 atm.withdraw(withdrawAmount);
                 break;
             case 2:
                 System.out.print("Enter deposit amount: ");
                 double depositAmount = scanner.nextDouble();
                 atm.deposit(depositAmount);
                 break;
             case 3:
                 atm.checkBalance();
                 break;
             case 4:
                 exit = true;
                 System.out.println("Exiting ATM. Thank you!");
                 break;
             default:
                 System.out.println("Invalid option. Please choose again.");
         }
     }

     scanner.close();
 }
}

