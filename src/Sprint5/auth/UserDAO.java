package Sprint5.auth;

public interface UserDAO {

    boolean register(String userNameRegisterRequest, String  password, int accountTypeSelection);

    User login(String username, String password);
}
