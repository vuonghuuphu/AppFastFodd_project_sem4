package huuphu.aprotrain.client_app.ViewModel;

import java.util.ArrayList;
import java.util.List;

import huuphu.aprotrain.client_app.Model.Category;
import huuphu.aprotrain.client_app.Model.Response.CategoryResponse;
import huuphu.aprotrain.client_app.Network.ApiManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel {
    static CategoryResponse categoryResponse = new CategoryResponse();
    public static void getCategories(){
        ApiManager.getService().getlistCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> CategoryList = response.body();
                System.out.println("body:"+ CategoryList.size());
            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
            }
        });
    }

}
