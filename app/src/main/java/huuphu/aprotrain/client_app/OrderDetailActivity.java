package huuphu.aprotrain.client_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import huuphu.aprotrain.client_app.Model.OrderDetails;
import huuphu.aprotrain.client_app.Model.OrderItem;
import huuphu.aprotrain.client_app.Model.OrderItemDetail;
import huuphu.aprotrain.client_app.Model.Product;
import huuphu.aprotrain.client_app.Model.ProductItem;
import huuphu.aprotrain.client_app.Model.Request.Oder_res;
import huuphu.aprotrain.client_app.Model.Request.Order_res;
import huuphu.aprotrain.client_app.Network.ApiManager;
import huuphu.aprotrain.client_app.View.Adapter.ListView.Order_Adapter;
import huuphu.aprotrain.client_app.View.Adapter.ListView.Order_detail_Adapter;
import huuphu.aprotrain.client_app.View.Fragment.fragment_cart;
import huuphu.aprotrain.client_app.View.Screen.CartActivity;
import huuphu.aprotrain.client_app.data.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailActivity extends AppCompatActivity {
RecyclerView recyclerView;
TextView tvId,tvDay,tvprice,tvStatus,btn_end_order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Intent getintent = getIntent();
        String id = getintent.getStringExtra("id_order");
        ImageView btnback = findViewById(R.id.btn_back_order_detail);
        ImageView iv_lt_orderdetail = findViewById(R.id.iv_lt_orderdetail);
        recyclerView = findViewById(R.id.rcv_order_detail);
        tvId = findViewById(R.id.tv_id_lst_orderdetail);
        tvDay = findViewById(R.id.tv_day_lst_orderdetail);
        tvStatus = findViewById(R.id.tv_shopdetail);
        tvprice = findViewById(R.id.tv_price_lst_orderdetail);
        btn_end_order = findViewById(R.id.btn_end_order);
        btnback.setOnClickListener(view -> finish());
        btn_end_order.setVisibility(View.GONE);
        Dialog dialogoload = new Dialog(OrderDetailActivity.this); // Context, this, etc.
        dialogoload.setContentView(R.layout.dialogloading);
        dialogoload.show();
        ApiManager.getService().getOrderDetail(id).enqueue(new Callback<OrderItem>() {
            List<ProductItem> lstProduct = new ArrayList<>();
            @Override
            public void onResponse(Call<OrderItem> call, Response<OrderItem> response) {
                OrderItem orderItem = response.body();
                tvId.setText("Mã: " + orderItem.id);
                String Price = NumberFormat.getNumberInstance(Locale.US).format(orderItem.totalPrice);
                tvprice.setText("Giá: " + Price + " vnđ");
                tvDay.setText("Ngày đặt: " + orderItem.createdAt);
                tvDay.setText(orderItem.createdAt);
                switch (orderItem.status){
                    case "WAITING":
                        iv_lt_orderdetail.setImageResource(R.drawable.sandclock);
                        tvStatus.setText("Đang chờ");
                        tvStatus.setTextColor(Color.BLACK);
                        btn_end_order.setVisibility(View.VISIBLE);
                        break;
                    case "DELIVERING":
                        iv_lt_orderdetail.setImageResource(R.drawable.fastdelivery);
                        btn_end_order.setVisibility(View.GONE);
                        tvStatus.setText("Đang giao hàng");
                        tvStatus.setTextColor(Color.BLACK);
                        break;
                    case "CONFIRMED":
                        iv_lt_orderdetail.setImageResource(R.drawable.shipped);
                        btn_end_order.setVisibility(View.GONE);
                        tvStatus.setText("Giao hàng thành công");
                        tvStatus.setTextColor(Color.BLACK);
                        break;
                    case "CANCELLED":
                        iv_lt_orderdetail.setImageResource(R.drawable.cancel);
                        btn_end_order.setVisibility(View.GONE);
                        tvStatus.setText("Đã Hủy");
                        tvStatus.setTextColor(Color.BLACK);
                        break;
                }

                btn_end_order.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Order_res oder_res = new Order_res(
                                orderItem.getAccountId(),
                                orderItem.getShipName(),
                                orderItem.getShipAddress(),
                                orderItem.getShipPhone(),
                                orderItem.getShipNote(),
                                orderItem.getPayment_method(),
                                "CANCELLED"
                        );
                        ApiManager.getService().EditOrder(orderItem.getId(),oder_res).enqueue(new Callback<OrderItem>() {
                            @Override
                            public void onResponse(Call<OrderItem> call, Response<OrderItem> response) {
                                if (response.code() == 200){
                                    Intent intent;
                                    intent = new Intent(OrderDetailActivity.this, MainActivity.class);
                                    intent.putExtra("navigation", 3 );
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onFailure(Call<OrderItem> call, Throwable t) {

                            }
                        });
                    }
                });


                for (OrderItemDetail order : orderItem.orderDetails) {
                    ApiManager.getService().getProductDetail(order.id.getProductId()).enqueue(new Callback<ProductItem>() {
                        @Override
                        public void onResponse(Call<ProductItem> call, Response<ProductItem> response) {
                            ProductItem item = response.body();
                            lstProduct.add(item);
                            int check = 0;
                            if (orderItem.status.equals("CONFIRMED")){
                                check = 1;
                            }
                            Order_detail_Adapter adapter = new Order_detail_Adapter(OrderDetailActivity.this,lstProduct,check);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(OrderDetailActivity.this,RecyclerView.VERTICAL,false);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                            dialogoload.cancel();
                        }
                        @Override
                        public void onFailure(Call<ProductItem> call, Throwable t) {
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<OrderItem> call, Throwable t) {
                Toast.makeText(OrderDetailActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }

}