import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

public class Bank implements Iterable<BankAccount> {
    private Map<Integer, BankAccount> accounts;
    private int nextacct;

    public Bank(Map<Integer, BankAccount> accounts, int n) {
        this.accounts = accounts;
        nextacct = n;
    }

    public int newAccount(int type, boolean isforeign) {
        int acctnum = nextacct++;
        BankAccount ba = AccountFactory.createAccount(type, acctnum);
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
        LoanAuthorizer auth = LoanAuthorizer.getAuthorizer(ba);
        return auth.authorizeLoan(loanamt);
    }

    public String toString() {
        String result = "The bank has " + accounts.size() + " accounts.";
        for (BankAccount ba : accounts.values())
            result += "\n\t" + ba.toString();
        return result;
    }

    public void addInterest() {
        for (BankAccount ba : accounts.values())
            ba.addInterest();
    }

    public int nextAcctNum() {
        return nextacct;
    }

    public Iterator<BankAccount> iterator() {
        Iterator<BankAccount> iter = accounts.values().iterator();
        return new UnmodifiableBankIterator(iter);
    }

    public Stream<BankAccount> stream() {
        return accounts.values().stream();
    }

    public Collection<Loan> loans() {
        return new ArrayList<Loan>();
    }

    public void makeSuspicious(int acctnum) {
        BankAccount ba = accounts.get(acctnum);
        ba = new SuspiciousAccount(ba);
        accounts.put(acctnum, ba);
    }
}



