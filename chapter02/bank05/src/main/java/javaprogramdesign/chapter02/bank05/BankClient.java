package javaprogramdesign.chapter02.bank05;

import java.util.Scanner;

public class BankClient {

    private Scanner scanner;
    private boolean done = false;
    private Bank bank;
    private int current = 0;

    public BankClient(Scanner scanner, Bank bank) {
        this.scanner = scanner;
        this.bank = bank;
    }

    public void run() {
        while (!done) {
            System.out.print("Enter command (0 = Quit, 1 = New, 2 = Select, 3 = Deposit, 4 = Loan, 5 = Show, 6 = Interest, 7 = Setforeign): ");
            int cnum = scanner.nextInt();
            processCommand(cnum);
        }
    }

    private void processCommand(int cnum) {
        if (cnum == 0) {
            quit();
        } else if (cnum == 1) {
            newAccount();
        } else if (cnum == 2) {
            select();
        } else if (cnum == 3) {
            deposit();
        } else if (cnum == 4) {
            authorizeLoan();
        } else if (cnum == 5) {
            showAll();
        } else if (cnum == 6) {
            addInterest();
        } else if (cnum == 7) {
            setForeign();
        } else {
            System.out.println("illegal command.");
        }
    }

    private void quit() {
        done = true;
        System.out.println("Goodbye!");
    }

    private void newAccount() {
        System.out.print("Enter account type (1 = Savings, 2 = Checking): ");
        int type = scanner.nextInt();
        boolean isforeign = requestForeign();
        current = bank.newAccount(type, isforeign);
        System.out.println("Your new account number is " + current);
    }

    private void select() {
        System.out.print("Enter account#: ");
        current = scanner.nextInt();
        int balance = bank.getBalance(current);
        System.out.println("The balance of account " + current + " is " + balance);
    }

    private void deposit() {
        System.out.print("Enter deposit amount: ");
        int amt = scanner.nextInt();
        bank.deposit(current, amt);
    }

    private void authorizeLoan() {
        System.out.print("Enter loan amount: ");
        int loanamt = scanner.nextInt();
        if (bank.authorizeLoan(current, loanamt))
            System.out.println("Your loan is approved.");
        else
            System.out.println("Your loan is denied.");
    }

    private void showAll() {
        System.out.println(bank.toString());
    }

    private void addInterest() {
        bank.addInterest();
    }

    private void setForeign() {
        bank.setForeign(current, requestForeign());
    }

    private boolean requestForeign() {
        System.out.print("Enter 1 for foreign, 2 for domestic: ");
        int val = scanner.nextInt();
        return val == 1;
    }

}
