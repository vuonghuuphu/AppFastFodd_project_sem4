package huuphu.aprotrain.client_app.Model;

public class OrderDetails {
    IdOrder id;
    private float quantity;
    private float unitPrice;

    public OrderDetails() {
    }

    public OrderDetails(IdOrder id, float quantity, float unitPrice) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    // Getter Methods

    public IdOrder getId() {
        return id;
    }

    public float getQuantity() {
        return quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    // Setter Methods

    public void setId(IdOrder idObject) {
        this.id = idObject;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }
}
