package huuphu.aprotrain.client_app.Model.Response;

import java.util.ArrayList;

public class Roles {
    public String id;
    public String name;
    public ArrayList<Permissions> permissions;

    // Getter Methods

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
