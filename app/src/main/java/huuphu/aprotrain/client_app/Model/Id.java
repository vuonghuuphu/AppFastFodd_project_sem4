package huuphu.aprotrain.client_app.Model;

public class Id {
    public String shoppingCartId;
    public String productId;

    public Id(String shoppingCartId, String productId) {
        this.shoppingCartId = shoppingCartId;
        this.productId = productId;
    }

    public Id() {
    }

    public String getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(String shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
