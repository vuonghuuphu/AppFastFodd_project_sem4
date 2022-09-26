package huuphu.aprotrain.client_app.Model.Request;

public class Account_cus {
    private String id_account;
    private String name;
    private String gender;
    private String email;
    private String address;
    private String phone_number;


    // Getter Methods

    public String getId_account() {
        return id_account;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone_number() {
        return phone_number;
    }


    // Setter Methods

    public void setId_account(String id_account) {
        this.id_account = id_account;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }


    public Account_cus(String id_account, String name, String gender, String email, String address, String phone_number) {
        this.id_account = id_account;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.phone_number = phone_number;
    }
}
