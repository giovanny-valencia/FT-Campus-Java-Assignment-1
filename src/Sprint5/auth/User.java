package Sprint5.auth;

public class User {
    private final String username;
    private final String password; // plainText storage and cannot be changed for simplicity

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return this.username;
    }

    // for login authentication
    public String getPassword(){
        return this.password;
    }

    public boolean hasClientRole(User user){
        return user instanceof Client;
    }
}
