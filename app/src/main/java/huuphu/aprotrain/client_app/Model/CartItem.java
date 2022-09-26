package huuphu.aprotrain.client_app.Model;

public class CartItem {
    Id id;
    private String productName;
    private String productThumbnail;
    private float quantity;
    private float unitPrice;
    private float status;
    private String cartItemStatus;


    // Getter Methods

    public Id getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductThumbnail() {
        return productThumbnail;
    }

    public float getQuantity() {
        return quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public float getStatus() {
        return status;
    }

    public String getCartItemStatus() {
        return cartItemStatus;
    }

    // Setter Methods

    public void setId(Id idObject) {
        this.id = idObject;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductThumbnail(String productThumbnail) {
        this.productThumbnail = productThumbnail;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setStatus(float status) {
        this.status = status;
    }

    public void setCartItemStatus(String cartItemStatus) {
        this.cartItemStatus = cartItemStatus;
    }
}
