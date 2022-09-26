package huuphu.aprotrain.client_app.Model.Response;

public class Permissions {
    public int id;
    public String name;
    public String url;
    public String method;


    // Getter Methods

    public float getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = (int) id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
