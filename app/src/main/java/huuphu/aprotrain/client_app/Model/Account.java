package huuphu.aprotrain.client_app.Model;

import java.util.ArrayList;

import huuphu.aprotrain.client_app.Model.Response.Roles;

public class Account {
    private String createdAt;
    private String updatedAt;
    private String deletedAt = null;
    private String createdBy = null;
    private String updatedBy = null;
    private String deletedBy = null;
    private String id;
    private String userName;
    private String passwordHash;
    ArrayList<Roles> roles = new ArrayList();
    private String status;

    public Account() {
    }

    public ArrayList<Roles> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Roles> roles) {
        this.roles = roles;
    }

    public Account(String createdAt, String updatedAt, String deletedAt, String createdBy, String updatedBy, String deletedBy, String id, String userName, String passwordHash, ArrayList<Roles> roles, String status) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.id = id;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.roles = roles;
        this.status = status;
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

    public String getUserName() {
        return userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getStatus() {
        return status;
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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
