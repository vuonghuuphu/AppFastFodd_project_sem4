package huuphu.aprotrain.client_app.Model.Request;

public class Oder_res {
    public String accountId;
    public String shipName;
    public String shipAddress;
    public String shipPhone;
    public String shipNote;
    public String payment_method;

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public Oder_res(String accountId, String shipName, String shipAddress, String shipPhone, String shipNote, String payment_method) {
        this.accountId = accountId;
        this.shipName = shipName;
        this.shipAddress = shipAddress;
        this.shipPhone = shipPhone;
        this.shipNote = shipNote;
        this.payment_method = payment_method;
    }

    public Oder_res() {
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
