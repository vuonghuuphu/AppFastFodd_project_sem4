package huuphu.aprotrain.client_app.View.Screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import huuphu.aprotrain.client_app.Model.Category;
import huuphu.aprotrain.client_app.Network.ApiManager;
import huuphu.aprotrain.client_app.ProductsActivity;
import huuphu.aprotrain.client_app.R;
import huuphu.aprotrain.client_app.View.Adapter.ListView.CategoriesAdapter;
import huuphu.aprotrain.client_app.View.Adapter.ListView.CategoriesHomeAdapter;
import huuphu.aprotrain.client_app.View.Fragment.fragment_home;
import huuphu.aprotrain.client_app.View.SpacesItemDecoration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListCategoriesActivity extends AppCompatActivity {

    List<Category> categoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_categories);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();

        ImageView btnback = findViewById(R.id.imv_cate_back);
        RecyclerView recyclerView = findViewById(R.id.rcv_cate_list);


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        ApiManager.getService().getlistCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@NonNull Call<List<Category>> call, @NonNull Response<List<Category>> response) {
                List<Category> CategoryList = response.body();
                CategoriesAdapter adapter_cate = new CategoriesAdapter(ListCategoriesActivity.this,CategoryList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListCategoriesActivity.this,RecyclerView.VERTICAL,false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter_cate);
                adapter_cate.setCategoriesOnclick(position -> {
                    Intent intent = new Intent(ListCategoriesActivity.this, ProductsActivity.class);
                    assert CategoryList != null;
                    intent.putExtra("id_cate", (int)CategoryList.get(position).getId());
                    startActivity(intent);
                });

            }
            @Override
            public void onFailure(@NonNull Call<List<Category>> call, @NonNull Throwable t) {
            }
        });
    }
}