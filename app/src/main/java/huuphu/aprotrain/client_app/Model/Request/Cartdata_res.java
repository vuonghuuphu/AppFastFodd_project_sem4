package huuphu.aprotrain.client_app.Model.Request;

public class Cartdata_res{
    private String productId;
    private float quantity;

    public Cartdata_res(String productId, float quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Cartdata_res() {
    }
    // Getter Methods

    public String getProductId() {
        return productId;
    }

    public float getQuantity() {
        return quantity;
    }

    // Setter Methods

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}
