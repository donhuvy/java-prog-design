import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class SavedBankInfo {

    private String fname;
    private Map<Integer, BankAccount> accounts;
    private int nextaccount;

    public SavedBankInfo(String fname) {
        this.fname = fname;
        File f = new File(fname);
        if (!f.exists()) {
            accounts = new HashMap<Integer, BankAccount>();
            nextaccount = 0;
        } else {
            try (InputStream is = new FileInputStream(fname);
                 ObjectInput ois = new ObjectInputStream(is)) {
                accounts = (Map<Integer, BankAccount>) ois.readObject();
                nextaccount = ois.readInt();
            } catch (Exception ex) {
                throw new RuntimeException("bank file read exception");
            }
        }
    }

    public Map<Integer, BankAccount> getAccounts() {
        return accounts;
    }

    public int nextAcctNum() {
        return nextaccount;
    }

    public void saveMap(Map<Integer, BankAccount> map, int nextaccount) {
        try (OutputStream os = new FileOutputStream(fname);
             ObjectOutput oos = new ObjectOutputStream(os)) {
            oos.writeObject(map);
            oos.writeInt(nextaccount);
        } catch (IOException ex) {
            throw new RuntimeException("bank file write exception");
        }
    }

}
