import java.util.HashMap;

public class Bank {
    private HashMap<String, Account> accounts = new HashMap<>();

    public boolean register(String username, String password) {
        if (accounts.containsKey(username)) {
            return false;
        }
        accounts.put(username, new Account(username, password));
        return true;
    }

    public Account login(String username, String password) {
        Account acc = accounts.get(username);
        if (acc != null && acc.checkPassword(password)) {
            return acc;
        }
        return null;
    }
}
