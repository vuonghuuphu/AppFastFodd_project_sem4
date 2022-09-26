package huuphu.aprotrain.client_app.Model;

public class OrderItemDetail {
    public IdOrder id;
    public int quantity;
    public double unitPrice;


    public IdOrder getId() {
        return id;
    }

    public void setId(IdOrder id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public OrderItemDetail() {
    }

    public OrderItemDetail(IdOrder id, int quantity, double unitPrice) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}
