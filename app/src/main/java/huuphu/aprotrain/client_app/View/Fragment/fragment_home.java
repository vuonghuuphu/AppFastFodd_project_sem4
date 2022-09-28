package huuphu.aprotrain.client_app.View.Fragment;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;
import huuphu.aprotrain.client_app.Model.Category;
import huuphu.aprotrain.client_app.Model.Product;
import huuphu.aprotrain.client_app.Model.ProductItem;
import huuphu.aprotrain.client_app.Model.Slide;
import huuphu.aprotrain.client_app.Network.ApiManager;
import huuphu.aprotrain.client_app.ProductsActivity;
import huuphu.aprotrain.client_app.R;
import huuphu.aprotrain.client_app.View.Adapter.GridSpacingItemDecoration;
import huuphu.aprotrain.client_app.View.Adapter.ListView.CategoriesHomeAdapter;
import huuphu.aprotrain.client_app.View.Adapter.ListView.ProductNewAdapter;
import huuphu.aprotrain.client_app.View.Adapter.ListView.ProductNewAdapterHo;
import huuphu.aprotrain.client_app.View.Adapter.Slider_Adapter;
import huuphu.aprotrain.client_app.View.Screen.ProductdetailActivity;
import huuphu.aprotrain.client_app.View.SpacesItemDecoration;
import huuphu.aprotrain.client_app.ViewModel.HomeViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment_home extends Fragment {
    RecyclerView recyclerView_cate ;
    RecyclerView recyclerView_Product ;
    RecyclerView recyclerView_Product_new;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        SliderView sliderView = view.findViewById(R.id.slider_home);

        Dialog dialogoload = new Dialog(view.getContext()); // Context, this, etc.
        dialogoload.setContentView(R.layout.dialogloading);
        dialogoload.show();

        recyclerView_cate = view.findViewById(R.id.rcv_listcategories);
        recyclerView_Product = view.findViewById(R.id.rcv_listproduct);
        recyclerView_Product_new = view.findViewById(R.id.rcv_listproduct_new);
        swipeRefreshLayout = view.findViewById(R.id.home_fresh);

        ApiManager.getService().getSlide().enqueue(new Callback<List<Slide>>() {
            @Override
            public void onResponse(Call<List<Slide>> call, Response<List<Slide>> response) {
                List<Slide> listImg = response.body();
                Slider_Adapter adapter = new Slider_Adapter(listImg);
                sliderView.setSliderAdapter(adapter);
                sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                sliderView.setIndicatorSelectedColor(Color.WHITE);
                sliderView.setIndicatorUnselectedColor(Color.GRAY);
                sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
                sliderView.startAutoCycle();
            }

            @Override
            public void onFailure(Call<List<Slide>> call, Throwable t) {

            }
        });


//        category
        ApiManager.getService().getlistCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@NonNull Call<List<Category>> call, @NonNull Response<List<Category>> response) {
                List<Category> CategoryList = response.body();
                CategoriesHomeAdapter adapter_cate = new CategoriesHomeAdapter(fragment_home.this,CategoryList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(container.getContext(),RecyclerView.HORIZONTAL,false);
                recyclerView_cate.setLayoutManager(layoutManager);
                recyclerView_cate.setAdapter(adapter_cate);
                adapter_cate.setCategoriesOnclick(position -> {
                Intent intent = new Intent(view.getContext(), ProductsActivity.class);
                    assert CategoryList != null;
                    System.out.println("ooopppooopp" + (int)CategoryList.get(position).getId());
                    intent.putExtra("id_cate", (int)CategoryList.get(position).getId());
                    startActivity(intent);
                });

            }
            @Override
            public void onFailure(@NonNull Call<List<Category>> call, @NonNull Throwable t) {
            }
        });

//        product
        ApiManager.getService().getlistProducts().enqueue(new Callback<List<ProductItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<ProductItem>> call, @NonNull Response<List<ProductItem>> response) {
                List<ProductItem> product = response.body();
                List<ProductItem> productItemList = new ArrayList<>();
                for (int i = product.size() -1; i >= 0 ; i--) {
                    productItemList.add(product.get(i));
                }
                ProductNewAdapterHo adapter_product = new ProductNewAdapterHo(fragment_home.this,productItemList);
                RecyclerView.LayoutManager layoutManager_product = new LinearLayoutManager(container.getContext(),RecyclerView.HORIZONTAL,false);
                recyclerView_Product.setLayoutManager(layoutManager_product);
                recyclerView_Product.setAdapter(adapter_product);
                adapter_product.setProductOnclick(position -> {
                    Intent intent = new Intent(view.getContext(), ProductdetailActivity.class);
                    intent.putExtra("id", productItemList.get(position).getId());
                    startActivity(intent);
                });
            }
            @Override
            public void onFailure(@NonNull Call<List<ProductItem>> call, @NonNull Throwable t) {
            }
        });

        int spanCount = 2; // 3 columns
        int spacing = 15; // 50px
        boolean includeEdge = false;
        recyclerView_Product_new.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

//        product new
        ApiManager.getService().getlistProducts().enqueue(new Callback<List<ProductItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<ProductItem>> call, @NonNull Response<List<ProductItem>> response) {
                List<ProductItem> products = response.body();
                assert products != null;
                ProductNewAdapter productNewAdapter = new ProductNewAdapter(fragment_home.this,products);
                RecyclerView.LayoutManager layoutManager_product_new = new GridLayoutManager(container.getContext(),2);
                recyclerView_Product_new.setLayoutManager(layoutManager_product_new);
                recyclerView_Product_new.setNestedScrollingEnabled(false);
                recyclerView_Product_new.setAdapter(productNewAdapter);
                int height_new = products.size() *320;
                recyclerView_Product_new.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT , height_new));
//                recyclerView_Product_new.addItemDecoration(new SpacesItemDecoration(7));
                productNewAdapter.setProductOnclick(position -> {
                    Intent intent = new Intent(view.getContext(), ProductdetailActivity.class);
                    System.out.println("q1"+ products.get(position).getId());
                    intent.putExtra("id", products.get(position).getId());
                    startActivity(intent);
                });
                dialogoload.cancel();
            }

            @Override
            public void onFailure(@NonNull Call<List<ProductItem>> call, @NonNull Throwable t) {
            }
        });
        HomeViewModel.getCategories();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ApiManager.getService().getSlide().enqueue(new Callback<List<Slide>>() {
                    @Override
                    public void onResponse(Call<List<Slide>> call, Response<List<Slide>> response) {
                        List<Slide> listImg = response.body();
                        Slider_Adapter adapter = new Slider_Adapter(listImg);
                        sliderView.setSliderAdapter(adapter);
                        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                        sliderView.setIndicatorSelectedColor(Color.WHITE);
                        sliderView.setIndicatorUnselectedColor(Color.GRAY);
                        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
                        sliderView.startAutoCycle();
                    }

                    @Override
                    public void onFailure(Call<List<Slide>> call, Throwable t) {

                    }
                });


//        category
                ApiManager.getService().getlistCategories().enqueue(new Callback<List<Category>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Category>> call, @NonNull Response<List<Category>> response) {
                        List<Category> CategoryList = response.body();
                        CategoriesHomeAdapter adapter_cate = new CategoriesHomeAdapter(fragment_home.this,CategoryList);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(container.getContext(),RecyclerView.HORIZONTAL,false);
                        recyclerView_cate.setLayoutManager(layoutManager);
                        recyclerView_cate.setAdapter(adapter_cate);
                        adapter_cate.setCategoriesOnclick(position -> {
                            Intent intent = new Intent(view.getContext(), ProductsActivity.class);
                            assert CategoryList != null;
                            System.out.println("ooopppooopp" + (int)CategoryList.get(position).getId());
                            intent.putExtra("id_cate", (int)CategoryList.get(position).getId());
                            startActivity(intent);
                        });

                    }
                    @Override
                    public void onFailure(@NonNull Call<List<Category>> call, @NonNull Throwable t) {
                    }
                });

//        product
                ApiManager.getService().getlistProducts().enqueue(new Callback<List<ProductItem>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<ProductItem>> call, @NonNull Response<List<ProductItem>> response) {
                        List<ProductItem> product = response.body();
                        List<ProductItem> productItemList = new ArrayList<>();
                        for (int i = product.size() -1; i >= 0 ; i--) {
                            productItemList.add(product.get(i));
                        }
                        ProductNewAdapterHo adapter_product = new ProductNewAdapterHo(fragment_home.this,productItemList);
                        RecyclerView.LayoutManager layoutManager_product = new LinearLayoutManager(container.getContext(),RecyclerView.HORIZONTAL,false);
                        recyclerView_Product.setLayoutManager(layoutManager_product);
                        recyclerView_Product.setAdapter(adapter_product);
                        adapter_product.setProductOnclick(position -> {
                            Intent intent = new Intent(view.getContext(), ProductdetailActivity.class);
                            intent.putExtra("id", productItemList.get(position).getId());
                            startActivity(intent);
                        });
                    }
                    @Override
                    public void onFailure(@NonNull Call<List<ProductItem>> call, @NonNull Throwable t) {
                    }
                });

//        product new
                ApiManager.getService().getlistProducts().enqueue(new Callback<List<ProductItem>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<ProductItem>> call, @NonNull Response<List<ProductItem>> response) {
                        List<ProductItem> products = response.body();
                        assert products != null;
                        ProductNewAdapter productNewAdapter = new ProductNewAdapter(fragment_home.this,products);
                        RecyclerView.LayoutManager layoutManager_product_new = new GridLayoutManager(container.getContext(),2);
                        recyclerView_Product_new.setLayoutManager(layoutManager_product_new);
                        recyclerView_Product_new.setNestedScrollingEnabled(false);
                        recyclerView_Product_new.setAdapter(productNewAdapter);
                        int height_new = products.size() *320;
                        recyclerView_Product_new.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT , height_new));
//                recyclerView_Product_new.addItemDecoration(new SpacesItemDecoration(7));
                        productNewAdapter.setProductOnclick(position -> {
                            Intent intent = new Intent(view.getContext(), ProductdetailActivity.class);
                            System.out.println("q1"+ products.get(position).getId());
                            intent.putExtra("id", products.get(position).getId());
                            startActivity(intent);
                        });
                        dialogoload.cancel();
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<ProductItem>> call, @NonNull Throwable t) {
                    }
                });
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }
}