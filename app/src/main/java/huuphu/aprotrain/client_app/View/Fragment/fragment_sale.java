package huuphu.aprotrain.client_app.View.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import huuphu.aprotrain.client_app.Model.Product;
import huuphu.aprotrain.client_app.Model.ProductItem;
import huuphu.aprotrain.client_app.Network.ApiManager;
import huuphu.aprotrain.client_app.R;
import huuphu.aprotrain.client_app.View.Adapter.ListView.ProductNewAdapter;
import huuphu.aprotrain.client_app.View.Adapter.ListView.ProductSaleAdapter;
import huuphu.aprotrain.client_app.View.Adapter.Slider_Adapter;
import huuphu.aprotrain.client_app.View.Onclick.ProductOnclick;
import huuphu.aprotrain.client_app.View.Screen.ProductdetailActivity;
import huuphu.aprotrain.client_app.View.SpacesItemDecoration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment_sale extends Fragment {

    RecyclerView recyclerView ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,@Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_sale, container, false);

        recyclerView = view.findViewById(R.id.rcv_list_sale);

        ApiManager.getService().getlistProducts().enqueue(new Callback<List<ProductItem>>() {
            @Override
            public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response) {
                List<ProductItem> products = response.body();
                List<ProductItem> productsSale = new ArrayList<>();
                for(int i = 0; i< Objects.requireNonNull(products).size(); i++){
                    if (products.get(i).getUnit_price() != 0){
                        productsSale.add(products.get(i));
                    }
                }
                ProductSaleAdapter productSaleAdapter = new ProductSaleAdapter(fragment_sale.this,productsSale);
                RecyclerView.LayoutManager layoutManager_product_new = new GridLayoutManager(container.getContext(),2);
                recyclerView.setLayoutManager(layoutManager_product_new);
                recyclerView.setNestedScrollingEnabled(false);
                recyclerView.setAdapter(productSaleAdapter);
                recyclerView.addItemDecoration(new SpacesItemDecoration(10));
                productSaleAdapter.setProductOnclick(new ProductOnclick() {
                    @Override
                    public void onClickitem(int position) {
                        Intent intent = new Intent(view.getContext(), ProductdetailActivity.class);
                        System.out.println("q1"+ products.get(position).getId());
                        intent.putExtra("id", products.get(position).getId());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<ProductItem>> call, Throwable t) {
            }
        });

        return view;
    }
}