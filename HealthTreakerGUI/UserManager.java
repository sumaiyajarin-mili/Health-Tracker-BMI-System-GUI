import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private Map<String, User> users = new HashMap<>();

    public boolean register(String username, String password) {
        if (users.containsKey(username)) return false;
        users.put(username, new User(username, password));
        return true;
    }

    public boolean login(String username, String password) {
        if (!users.containsKey(username)) return false;
        return users.get(username).getPassword().equals(password);
    }
}

