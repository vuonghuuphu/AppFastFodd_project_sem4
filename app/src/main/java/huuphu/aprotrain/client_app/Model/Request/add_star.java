package huuphu.aprotrain.client_app.Model.Request;

public class add_star {
    private float rating_star;
    private String id_product;
    private String id_account;

    public add_star(float rating_star, String id_product, String id_account) {
        this.rating_star = rating_star;
        this.id_product = id_product;
        this.id_account = id_account;
    }

    // Getter Methods

    public float getRating_star() {
        return rating_star;
    }

    public String getId_product() {
        return id_product;
    }

    public String getId_account() {
        return id_account;
    }

    // Setter Methods

    public void setRating_star(float rating_star) {
        this.rating_star = rating_star;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public void setId_account(String id_account) {
        this.id_account = id_account;
    }
}
