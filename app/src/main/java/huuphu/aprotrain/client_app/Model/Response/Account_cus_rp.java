package huuphu.aprotrain.client_app.Model.Response;

public class Account_cus_rp {
    private float id;
    private String id_account;
    private String name;
    private String gender;
    private String email;
    private String address;
    private String phone_number;

    @Override
    public String toString() {
        return "Account_cus_rp{" +
                "id=" + id +
                ", id_account='" + id_account + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone_number='" + phone_number + '\'' +
                '}';
    }

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public String getId_account() {
        return id_account;
    }

    public void setId_account(String id_account) {
        this.id_account = id_account;
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

    public Account_cus_rp(float id, String id_account, String name, String gender, String email, String address, String phone_number) {
        this.id = id;
        this.id_account = id_account;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.phone_number = phone_number;
    }

}
