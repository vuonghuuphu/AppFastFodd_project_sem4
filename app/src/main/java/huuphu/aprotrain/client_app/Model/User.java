package huuphu.aprotrain.client_app.Model;

import java.util.ArrayList;

import huuphu.aprotrain.client_app.Model.Response.Roles;

public class User {
    public String id;
    public String username;
    public ArrayList<Roles> roles;
    // Getter Methods

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
