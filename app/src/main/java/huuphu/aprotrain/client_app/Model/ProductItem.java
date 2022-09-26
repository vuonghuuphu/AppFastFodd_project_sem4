package huuphu.aprotrain.client_app.Model;

public class ProductItem {
    private String createdAt;
    private String updatedAt;
    private String deletedAt = null;
    private String createdBy = null;
    private String updatedBy = null;
    private String deletedBy = null;
    private String id;
    private String name;
    private String thumbnail;
    private String description;
    private float unit_price;
    private float promotion_price;
    private float qty;
    private String slug;
    private String status;
    private float categoryId;
    Category CategoryObject;


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

    public String getName() {
        return name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public float getUnit_price() {
        return unit_price;
    }

    public float getPromotion_price() {
        return promotion_price;
    }

    public float getQty() {
        return qty;
    }

    public String getSlug() {
        return slug;
    }

    public String getStatus() {
        return status;
    }

    public float getCategoryId() {
        return categoryId;
    }

    public Category getCategory() {
        return CategoryObject;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUnit_price(float unit_price) {
        this.unit_price = unit_price;
    }

    public void setPromotion_price(float promotion_price) {
        this.promotion_price = promotion_price;
    }

    public void setQty(float qty) {
        this.qty = qty;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCategoryId(float categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategory(Category categoryObject) {
        this.CategoryObject = categoryObject;
    }
}
