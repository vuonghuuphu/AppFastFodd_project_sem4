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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import huuphu.aprotrain.client_app.Model.Product;
import huuphu.aprotrain.client_app.Model.ProductItem;
import huuphu.aprotrain.client_app.Network.ApiManager;
import huuphu.aprotrain.client_app.View.Adapter.GridSpacingItemDecoration;
import huuphu.aprotrain.client_app.View.Adapter.ListView.ProductNewAdapter;
import huuphu.aprotrain.client_app.View.Adapter.ListView.ProductsAdapter;
import huuphu.aprotrain.client_app.View.Onclick.ProductOnclick;
import huuphu.aprotrain.client_app.View.Screen.ProductdetailActivity;
import huuphu.aprotrain.client_app.View.SpacesItemDecoration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsActivity extends AppCompatActivity {
    List<Product> productList = new ArrayList<>();
    ImageView btn_back ;
    RecyclerView recyclerViewProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide()  ;

        btn_back = findViewById(R.id.btn_back_products);
        RecyclerView recyclerViewProducts = findViewById(R.id.rcv_listproducts);

        Intent getintent = getIntent();
        int id = getintent.getIntExtra("id_cate",0);

        ApiManager.getService().getPrductCate(""+id).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Product productItemList = response.body();
                ProductsAdapter productNewAdapter = new ProductsAdapter(ProductsActivity.this,productItemList.getContent());
                RecyclerView.LayoutManager layoutManager_product_new = new GridLayoutManager(ProductsActivity.this,2);
                recyclerViewProducts.setLayoutManager(layoutManager_product_new);
                recyclerViewProducts.setNestedScrollingEnabled(false);
                recyclerViewProducts.setAdapter(productNewAdapter);
                int spanCount = 2; // 3 columns
                int spacing = 15; // 50px
                boolean includeEdge = false;
                recyclerViewProducts.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
                productNewAdapter.setProductOnclick(new ProductOnclick() {
                    @Override
                    public void onClickitem(int position) {
                        Intent intent = new Intent(ProductsActivity.this, ProductdetailActivity.class);
                        assert productItemList != null;
                        intent.putExtra("id", productItemList.getContent().get(position).getId());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}