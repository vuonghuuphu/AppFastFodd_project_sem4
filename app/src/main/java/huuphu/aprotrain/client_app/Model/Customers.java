package huuphu.aprotrain.client_app.Model;

public class Customers {
    public int id;
    public String id_account;
    public Account account;
    public String name;
    public String gender;
    public String email;
    public String address;
    public String phone_number;
    public String status;

    @Override
    public String toString() {
        return "Customers{" +
                "id=" + id +
                ", id_account='" + id_account + '\'' +
                ", account=" + account +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_account() {
        return id_account;
    }

    public void setId_account(String id_account) {
        this.id_account = id_account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customers() {
    }

    public Customers(int id, String id_account, Account account, String name, String gender, String email, String address, String phone_number, String status) {
        this.id = id;
        this.id_account = id_account;
        this.account = account;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.phone_number = phone_number;
        this.status = status;
    }
}
