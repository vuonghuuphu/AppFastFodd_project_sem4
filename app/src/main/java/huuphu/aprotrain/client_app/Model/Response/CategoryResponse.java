package huuphu.aprotrain.client_app.Model.Response;

import java.util.List;

import huuphu.aprotrain.client_app.Model.Category;

public class CategoryResponse {
    private List<Category> categoryList ;

    public CategoryResponse(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public CategoryResponse() {
    }
}