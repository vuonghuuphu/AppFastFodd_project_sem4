package huuphu.aprotrain.client_app.Model;

public class Comments {
    private float id;
    private String content;
    private String id_account;
    private String id_product;
    private String status;

    // Getter Methods

    public float getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getId_account() {
        return id_account;
    }

    public String getId_product() {
        return id_product;
    }

    public String getStatus() {
        return status;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId_account(String id_account) {
        this.id_account = id_account;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
