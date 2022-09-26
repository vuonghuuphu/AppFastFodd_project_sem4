package huuphu.aprotrain.client_app.Model;

import java.util.ArrayList;
import java.util.Date;

public class Cart {
    private String createdAt;
    private String updatedAt;
    private String deletedAt = null;
    private String createdBy = null;
    private String updatedBy = null;
    private String deletedBy = null;
    private String id;
    private String accountId;
    private float totalPrice;
    ArrayList < CartItem > cartItems = new ArrayList < CartItem>();

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(ArrayList<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    // Getter Methods

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public String getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    // Setter Methods

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}