package javaprogramdesign.chapter02.bank05;

import java.util.HashMap;

public class Bank {

    private HashMap<Integer, BankAccount> accounts;
    private int nextacct;

    public Bank(HashMap<Integer, BankAccount> accounts, int n) {
        this.accounts = accounts;
        nextacct = n;
    }

    public int newAccount(int type, boolean isforeign) {
        int acctnum = nextacct++;
        BankAccount ba;
        if (type == 1) {
            ba = new SavingsAccount(acctnum);
        } else {
            ba = new CheckingAccount(acctnum);
        }
        ba.setForeign(isforeign);
        accounts.put(acctnum, ba);
        return acctnum;
    }

    public int getBalance(int acctnum) {
        BankAccount ba = accounts.get(acctnum);
        return ba.getBalance();
    }

    public void setForeign(int acctnum, boolean isforeign) {
        BankAccount ba = accounts.get(acctnum);
        ba.setForeign(isforeign);
    }

    public void deposit(int acctnum, int amt) {
        BankAccount ba = accounts.get(acctnum);
        ba.deposit(amt);
    }

    public boolean authorizeLoan(int acctnum, int loanamt) {
        BankAccount ba = accounts.get(acctnum);
        return ba.hasEnoughCollateral(loanamt);
    }

    public String toString() {
        String result = "The bank has " + accounts.size() + " accounts.";
        for (BankAccount ba : accounts.values())
            result += "\n\t" + ba.toString();
        return result;
    }

    public void addInterest() {
        for (BankAccount ba : accounts.values())
            if (ba instanceof SavingsAccount) {
                SavingsAccount sa = (SavingsAccount) ba;
                sa.addInterest();
            }
    }

}



