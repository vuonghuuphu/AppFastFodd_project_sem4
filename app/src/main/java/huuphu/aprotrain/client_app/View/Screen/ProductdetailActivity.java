package huuphu.aprotrain.client_app.View.Screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import huuphu.aprotrain.client_app.MainActivity;
import huuphu.aprotrain.client_app.Model.Cart;
import huuphu.aprotrain.client_app.Model.Comments;
import huuphu.aprotrain.client_app.Model.Product;
import huuphu.aprotrain.client_app.Model.ProductItem;
import huuphu.aprotrain.client_app.Model.Request.Cart_res;
import huuphu.aprotrain.client_app.Model.Request.Cartdata_res;
import huuphu.aprotrain.client_app.Model.Request.Comment_res;
import huuphu.aprotrain.client_app.Model.Star;
import huuphu.aprotrain.client_app.Network.ApiManager;
import huuphu.aprotrain.client_app.Order_dis_Activity;
import huuphu.aprotrain.client_app.R;
import huuphu.aprotrain.client_app.View.Adapter.ListView.CommentAdapter;
import huuphu.aprotrain.client_app.View.Adapter.ListView.ProductOtherAdapter;
import huuphu.aprotrain.client_app.View.Adapter.Slider_detail_Adapter;
import huuphu.aprotrain.client_app.data.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductdetailActivity extends AppCompatActivity {
    List<Product> productList = new ArrayList<>();
    RecyclerView recyclerView_product;
    RecyclerView recyclerView_img,rcv_comment;
    ImageView btnBack,btn_star_1,btn_star_2,btn_star_3,btn_star_4,btn_star_5,btn_2;
    TextView tvname ,tvprice,tvUnit_price,tvProductDetailDes,count_point,btn_rep;
    Button button_add_cart;
    LinearLayout layout_comment_on,layout_comment_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetail);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();

        System.out.println("id_usser1= "+ Constants.idUser);
        System.out.println("id_usser1= "+ Constants.token);

        btnBack = findViewById(R.id.btn_back);
        btn_rep = findViewById(R.id.btn_rep);
        recyclerView_img = findViewById(R.id.rcv_list_images_detail);
        recyclerView_product = findViewById(R.id.rcv_list_product_other);
        tvname = findViewById(R.id.product_detail_name);
        tvprice = findViewById(R.id.product_detail_price);
        tvUnit_price = findViewById(R.id.product_detail_priceUn);
        tvProductDetailDes = findViewById(R.id.product_detail_des);
        btn_star_1 = findViewById(R.id.btn_star_1);
        btn_star_2 = findViewById(R.id.btn_star_2);
        btn_star_3 = findViewById(R.id.btn_star_3);
        btn_star_4 = findViewById(R.id.btn_star_4);
        btn_star_5 = findViewById(R.id.btn_star_5);
        count_point = findViewById(R.id.count_point);
        rcv_comment = findViewById(R.id.rcv_comment);
        btn_2 = findViewById(R.id.btn_2);
        layout_comment_on = findViewById(R.id.layout_comment_on);
        layout_comment_no = findViewById(R.id.layout_comment_no);

        layout_comment_no.setVisibility(View.GONE);
        layout_comment_on.setVisibility(View.VISIBLE);
//        layout_comment_on.setVisibility(View.GONE);

        Dialog dialog = new Dialog(ProductdetailActivity.this);
        Intent getintent = getIntent();
        String id = getintent.getStringExtra("id");

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Constants.token == null) {
                    Dialog dialog = new Dialog(ProductdetailActivity.this);
                    dialog.setContentView(R.layout.dl_login);
                    dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    dialog.show();
                    Handler mHandler = new Handler();
                    mHandler.postDelayed(() -> {
                        dialog.cancel();
                    }, 2000);
                } else {
                    Intent intent = new Intent(ProductdetailActivity.this, CartActivity.class);
                    startActivity(intent);
                }
            }
        });

        btn_rep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Constants.token == null) {
                    Dialog dialog = new Dialog(ProductdetailActivity.this);
                    dialog.setContentView(R.layout.dl_login);
                    dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    dialog.show();
                    Handler mHandler = new Handler();
                    mHandler.postDelayed(() -> {
                        dialog.cancel();
                    }, 2000);
                } else {
                    dialog.setContentView(R.layout.dl_add_comment);
                    dialog.getWindow().setLayout(view.getLayoutParams().MATCH_PARENT, view.getLayoutParams().WRAP_CONTENT);
                    dialog.setCancelable(false);

                    Button btn_add = dialog.findViewById(R.id.button_send_comment);
                    Button btn_close = dialog.findViewById(R.id.button_close_comment);
                    EditText textView_comment = dialog.findViewById(R.id.textView_comment);

                    btn_close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });

                    btn_add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Comment_res comment_res = new Comment_res(textView_comment.getText().toString(), Constants.idUser, id);
                            ApiManager.getService().AddComment(comment_res).enqueue(new Callback<Comment_res>() {
                                @Override
                                public void onResponse(Call<Comment_res> call, Response<Comment_res> response) {
                                    dialog.cancel();
                                    ApiManager.getService().getComments().enqueue(new Callback<List<Comments>>() {
                                        @Override
                                        public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                                            List<Comments> comments = response.body();
                                            List<Comments> comments1 = new ArrayList<>();
                                            for (int i = comments.size() -1; i >= 0; i--) {
                                                Comments comment = comments.get(i);
                                                if (Objects.equals(comments.get(i).getId_product(), id)) {
                                                    comments1.add(comment);
                                                }
                                            }
                                            CommentAdapter productOtherAdapter = new CommentAdapter(ProductdetailActivity.this, comments1);
                                            RecyclerView.LayoutManager layoutManager_product_new = new LinearLayoutManager(ProductdetailActivity.this, RecyclerView.VERTICAL, false);
                                            rcv_comment.setLayoutManager(layoutManager_product_new);
                                            rcv_comment.setAdapter(productOtherAdapter);
                                        }

                                        @Override
                                        public void onFailure(Call<List<Comments>> call, Throwable t) {
                                            System.out.println("Comments = " + "e");
                                        }
                                    });
                                }

                                @Override
                                public void onFailure(Call<Comment_res> call, Throwable t) {

                                }
                            });
                        }
                    });
                    dialog.show();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });

        ApiManager.getService().getStar().enqueue(new Callback<List<Star>>() {
            @Override
            public void onResponse(Call<List<Star>> call, Response<List<Star>> response) {
                List<Star> starList = response.body();
                int count = 0;
                for (Star star : starList) {
                    if (Objects.equals(star.id_product, id)){
                        int Point = (int) star.rating_star;
                        count = count+1;
                        switch (Point){
                            case 1:
                                btn_star_1.setImageResource(R.drawable.star);
                                break;
                            case 2:
                                btn_star_1.setImageResource(R.drawable.star);
                                btn_star_2.setImageResource(R.drawable.star);
                                break;
                            case 3:
                                btn_star_1.setImageResource(R.drawable.star);
                                btn_star_2.setImageResource(R.drawable.star);
                                btn_star_3.setImageResource(R.drawable.star);
                                break;
                            case 4:
                                btn_star_1.setImageResource(R.drawable.star);
                                btn_star_2.setImageResource(R.drawable.star);
                                btn_star_3.setImageResource(R.drawable.star);
                                btn_star_4.setImageResource(R.drawable.star);
                                break;
                            case 5:
                                btn_star_1.setImageResource(R.drawable.star);
                                btn_star_2.setImageResource(R.drawable.star);
                                btn_star_3.setImageResource(R.drawable.star);
                                btn_star_4.setImageResource(R.drawable.star);
                                btn_star_5.setImageResource(R.drawable.star);
                                break;
                        }
                    }
                }
                count_point.setText("( "+count+" )");
            }

            @Override
            public void onFailure(Call<List<Star>> call, Throwable t) {
                System.out.println("StarApi" + " e");
            }
        });

        ApiManager.getService().getProductDetail(id).enqueue(new Callback<ProductItem>() {
            @Override
            public void onResponse(Call<ProductItem> call, Response<ProductItem> response) {
                List<String> listImg = new ArrayList<>();
                ProductItem product = response.body();
                assert product != null;
                listImg.add(product.getThumbnail());
                SliderView sliderView = findViewById(R.id.slider_detai_product);
                Slider_detail_Adapter adapter = new Slider_detail_Adapter(listImg);
                sliderView.setSliderAdapter(adapter);
                sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                sliderView.setIndicatorSelectedColor(Color.WHITE);
                sliderView.setIndicatorUnselectedColor(Color.GRAY);
                sliderView.setScrollTimeInSec(5000); //set scroll delay in seconds :
                sliderView.startAutoCycle();


                tvname.setText(product.getName());
                String Price = NumberFormat.getNumberInstance(Locale.US).format(product.getPromotion_price());
                tvprice.setText(Price+" vnđ");
                String Price_un = NumberFormat.getNumberInstance(Locale.US).format(product.getUnit_price());
                tvUnit_price.setText(Price_un+" vnđ");
                tvUnit_price.setPaintFlags(tvUnit_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                tvProductDetailDes.setText(product.getDescription());
                button_add_cart = findViewById(R.id.button_add_cart);
                button_add_cart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Constants.token == null){
                            Dialog dialog = new Dialog(ProductdetailActivity.this);
                            dialog.setContentView(R.layout.dl_login);
                            dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                            dialog.show();
                            Handler mHandler = new Handler();
                            mHandler.postDelayed(() -> {
                            dialog.cancel();
                            }, 2000);
                        }else {
                            ArrayList<Cartdata_res> cartItemDTOSet = new ArrayList();
                            cartItemDTOSet.add(new Cartdata_res(
                                    product.getId(), 1
                            ));
                            Cart_res cart_res = new Cart_res();
                            cart_res.setCartItemDTOSet(cartItemDTOSet);
                            ApiManager.getService().AddCart(cart_res).enqueue(new Callback<Cart>() {
                                @Override
                                public void onResponse(Call<Cart> call, Response<Cart> response) {
                                    Cart cart = response.body();
                                    System.out.println("Đã thêm sản phẩm vào giỏ hàng" + response.code());
                                    Toast.makeText(ProductdetailActivity.this, "Đã thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                                }
                                @Override
                                public void onFailure(Call<Cart> call, Throwable t) {

                                }
                            });
                        }
                    }

                });
            }
            @Override
            public void onFailure(Call<ProductItem> call, Throwable t) {

            }
        });

        ApiManager.getService().getlistProducts().enqueue(new Callback<List<ProductItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<ProductItem>> call, @NonNull Response<List<ProductItem>> response) {
                List<ProductItem> products = response.body();
                assert products != null;
                ProductOtherAdapter productOtherAdapter = new ProductOtherAdapter(ProductdetailActivity.this,products);
                RecyclerView.LayoutManager layoutManager_product_new = new LinearLayoutManager(ProductdetailActivity.this,RecyclerView.HORIZONTAL,false);
                recyclerView_product.setLayoutManager(layoutManager_product_new);
                recyclerView_product.setAdapter(productOtherAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<ProductItem>> call, @NonNull Throwable t) {
            }
        });

        ApiManager.getService().getComments().enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                List<Comments> comments = response.body();
                List<Comments> comments1 = new ArrayList<>();
                for (int i = comments.size() -1; i>= 0;i--) {
                    Comments comment = comments.get(i);
                    if (Objects.equals(comments.get(i).getId_product(), id)){
                        comments1.add(comment);
                        CommentAdapter productOtherAdapter = new CommentAdapter(ProductdetailActivity.this,comments1);
                        RecyclerView.LayoutManager layoutManager_product_new = new LinearLayoutManager(ProductdetailActivity.this,RecyclerView.VERTICAL,false);
                        rcv_comment.setLayoutManager(layoutManager_product_new);
                        rcv_comment.setAdapter(productOtherAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                System.out.println("Comments = "+"e");
            }
        });

    }
}