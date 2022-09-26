package huuphu.aprotrain.client_app.Model;

import java.sql.Date;
import java.util.ArrayList;

public class OrderItem {
    public String createdAt;
    public String updatedAt;
    public String deletedAt = null;
    public String createdBy;
    public String updatedBy;
    public String deletedBy = null;
    public String id;
    public String accountId;
    public Account account;
    public ArrayList<OrderItemDetail> orderDetails;
    public double totalPrice;
    public String paid = null;
    public String unpaid = null;
    public String payment_method = null;
    public String status;
    public String shipName;
    public String shipAddress;
    public String shipPhone;
    public String shipNote;



    public OrderItem() {
    }

    public OrderItem(String createdAt, String updatedAt, String deletedAt, String createdBy, String updatedBy, String deletedBy, String id, String accountId, Account account, ArrayList<OrderItemDetail> orderDetails, double totalPrice, String paid, String unpaid, String payment_method, String status, String shipName, String shipAddress, String shipPhone, String shipNote) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.id = id;
        this.accountId = accountId;
        this.account = account;
        this.orderDetails = orderDetails;
        this.totalPrice = totalPrice;
        this.paid = paid;
        this.unpaid = unpaid;
        this.payment_method = payment_method;
        this.status = status;
        this.shipName = shipName;
        this.shipAddress = shipAddress;
        this.shipPhone = shipPhone;
        this.shipNote = shipNote;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ArrayList<OrderItemDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<OrderItemDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getUnpaid() {
        return unpaid;
    }

    public void setUnpaid(String unpaid) {
        this.unpaid = unpaid;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getShipPhone() {
        return shipPhone;
    }

    public void setShipPhone(String shipPhone) {
        this.shipPhone = shipPhone;
    }

    public String getShipNote() {
        return shipNote;
    }

    public void setShipNote(String shipNote) {
        this.shipNote = shipNote;
    }
}
