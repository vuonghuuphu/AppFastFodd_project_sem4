package huuphu.aprotrain.client_app.Model.Request;

public class Rep_res {
    private String content;
    private float id_comment;
    private String id_account;
    private String id_product;

    public Rep_res(String content, float id_comment, String id_account, String id_product) {
        this.content = content;
        this.id_comment = id_comment;
        this.id_account = id_account;
        this.id_product = id_product;
    }

    // Getter Methods

    public String getContent() {
        return content;
    }

    public float getId_comment() {
        return id_comment;
    }

    public String getId_account() {
        return id_account;
    }

    public String getId_product() {
        return id_product;
    }

    // Setter Methods

    public void setContent(String content) {
        this.content = content;
    }

    public void setId_comment(float id_comment) {
        this.id_comment = id_comment;
    }

    public void setId_account(String id_account) {
        this.id_account = id_account;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }
}
