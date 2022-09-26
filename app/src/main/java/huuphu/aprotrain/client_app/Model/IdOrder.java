package huuphu.aprotrain.client_app.Model;

public class IdOrder {
    private String productId;
    private String orderId;


    // Getter Methods


    public IdOrder() {
    }

    public IdOrder(String productId, String orderId) {
        this.productId = productId;
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public String getOrderId() {
        return orderId;
    }

    // Setter Methods

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
