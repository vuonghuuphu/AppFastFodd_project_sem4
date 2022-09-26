package huuphu.aprotrain.client_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.List;
import java.util.Objects;

import huuphu.aprotrain.client_app.Model.ProductItem;
import huuphu.aprotrain.client_app.Network.ApiManager;
import huuphu.aprotrain.client_app.View.Adapter.ListView.ProductNewAdapter;
import huuphu.aprotrain.client_app.View.Adapter.ListView.ProductSearchAdapter;
import huuphu.aprotrain.client_app.View.Fragment.fragment_home;
import huuphu.aprotrain.client_app.View.Screen.ProductdetailActivity;
import huuphu.aprotrain.client_app.View.SpacesItemDecoration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity{

    SearchView searchView;
    RecyclerView recyclerView;
    ImageView btn_back_search_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide() ;
        searchView = findViewById(R.id.searchActi_search);
        searchView.setQueryHint("Nhập sản phẩm cần tìm");
        recyclerView = findViewById(R.id.rcv_search);
        btn_back_search_detail = findViewById(R.id.btn_back_search_detail);

        btn_back_search_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ApiManager.getService().getlistProducts().enqueue(new Callback<List<ProductItem>>() {
            @Override
            public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response) {
                List<ProductItem> productItemList = response.body();
                ProductSearchAdapter productNewAdapter = new ProductSearchAdapter(SearchActivity.this,productItemList);
                RecyclerView.LayoutManager layoutManager_product_new = new GridLayoutManager(SearchActivity.this,2);
                recyclerView.setLayoutManager(layoutManager_product_new);
                recyclerView.setAdapter(productNewAdapter);
                recyclerView.addItemDecoration(new SpacesItemDecoration(10));
                productNewAdapter.setProductOnclick(position -> {
                    Intent intent = new Intent(SearchActivity.this, ProductdetailActivity.class);
                    System.out.println("q1"+ productItemList.get(position).getId());
                    intent.putExtra("id", productItemList.get(position).getId());
                    startActivity(intent);
                });

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        productNewAdapter.getFilter().filter(newText);
                        return false;
                    }
                });

            }

            @Override
            public void onFailure(Call<List<ProductItem>> call, Throwable t) {

            }
        });

    }
}