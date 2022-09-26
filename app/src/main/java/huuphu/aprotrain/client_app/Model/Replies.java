package huuphu.aprotrain.client_app.Model;

public class Replies {
    public int id;
    public String content;
    public int id_comment;
    public String id_account;
    public Account account;
    public String id_product;
    public String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId_comment() {
        return id_comment;
    }

    public void setId_comment(int id_comment) {
        this.id_comment = id_comment;
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

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Replies() {
    }

    public Replies(int id, String content, int id_comment, String id_account, Account account, String id_product, String status) {
        this.id = id;
        this.content = content;
        this.id_comment = id_comment;
        this.id_account = id_account;
        this.account = account;
        this.id_product = id_product;
        this.status = status;
    }
}

