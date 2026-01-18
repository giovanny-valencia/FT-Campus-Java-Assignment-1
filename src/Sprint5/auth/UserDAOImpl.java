package Sprint5.auth;

import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    private final ArrayList<User> users = new ArrayList<>();

    @Override
    public boolean register(String userNameRegisterRequest, String  password, int accountTypeSelection) {
        final int Client = 1;
        final int Visitor = 2;

        if (!isDataValid(userNameRegisterRequest, password, accountTypeSelection)) return false;

        boolean userExists = users.stream()
                .anyMatch(user -> user.getUsername().equalsIgnoreCase(userNameRegisterRequest));

        if (userExists) return false;

        if (accountTypeSelection == Client){
            return users.add(new Client(userNameRegisterRequest, password));
        } else if (accountTypeSelection == Visitor){
            return users.add(new Visitor(userNameRegisterRequest, password));
        }

        return false; // shouldn't happen
    }

    @Override
    public User login(String username, String password) {
        if (!isDataValid(username, password)) return null;

        return users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username)
                        && user.getPassword().equalsIgnoreCase(password))
                .findFirst()
                .orElse(null);
    }

    private boolean isDataValid(String userNameRegisterRequest, String  password, int accountTypeSelection){
        return !userNameRegisterRequest.isEmpty()
                && !password.isEmpty()
                && (accountTypeSelection == 1 || accountTypeSelection == 2);
    }

    private boolean isDataValid(String userNameRegisterRequest, String  password){
        return !userNameRegisterRequest.isEmpty()
                && !password.isEmpty();
    }
}
