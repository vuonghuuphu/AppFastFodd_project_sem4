package huuphu.aprotrain.client_app.Model;

public class Category {
    private String createdAt;
    private String updatedAt;
    private String deletedAt = null;
    private String createdBy = null;
    private String updatedBy = null;
    private String deletedBy = null;
    private float id;
    private String name;
    private String thumbnail;
    private String status;


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

    public float getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getThumbnail() {
        return thumbnail;
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

    public void setId(float id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Category(String createdAt, String updatedAt, String deletedAt, String createdBy, String updatedBy, String deletedBy, float id, String name, String thumbnail, String status) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
        this.status = status;
    }

    public Category() {
    }
}