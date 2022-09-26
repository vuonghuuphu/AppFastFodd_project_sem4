package huuphu.aprotrain.client_app.Model.Request;

public class LoginRequest {
    private String passwordHash;
    private String userName;

    public LoginRequest(String username, String password) {
        this.userName = username;
        this.passwordHash = password;
    }

    public LoginRequest() {
    }

    // Getter Methods
    public String getUsername() {
        return userName;
    }

    public String getPassword() {
        return passwordHash;
    }

    // Setter Methods
    public void setUsername(String username) {
        this.userName = username;
    }

    public void setPassword(String password) {
        this.passwordHash = password;
    }

}
