package huuphu.aprotrain.client_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import huuphu.aprotrain.client_app.Model.ListOrder;
import huuphu.aprotrain.client_app.Model.OrderItem;
import huuphu.aprotrain.client_app.Network.ApiManager;
import huuphu.aprotrain.client_app.View.Adapter.ListView.Order_Adapter;
import huuphu.aprotrain.client_app.View.Adapter.ListView.Order_Diss_Adapter;
import huuphu.aprotrain.client_app.View.Fragment.fragment_cart;
import huuphu.aprotrain.client_app.View.Screen.CartActivity;
import huuphu.aprotrain.client_app.data.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Order_dis_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_dis);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();

        RecyclerView recyclerView = findViewById(R.id.rcv_order_dis);
        LinearLayout linearLayout = findViewById(R.id.ll_emty_order_diss);
        ImageView imageView = findViewById(R.id.btn_back_order_dis);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (Constants.token == null){
            Dialog dialog = new Dialog(Order_dis_Activity.this);
            dialog.setContentView(R.layout.dl_login);
            dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            dialog.show();
            Handler mHandler = new Handler();
            mHandler.postDelayed(() -> {
                Intent intent;
                intent = new Intent(Order_dis_Activity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }, 2000);
        }else {
        ApiManager.getService().getOrder().enqueue(new Callback<ListOrder>() {
            @Override
            public void onResponse(Call<ListOrder> call, Response<ListOrder> response) {
                ListOrder order = response.body();
                List<OrderItem> orderItems = new ArrayList<>();
                if (order.content != null){
                    for (int i = 0; i < order.content.size(); i++) {
                        if (order.content.get(i).accountId.equals(Constants.idUser)&& order.content.get(i).status.equals("CANCELLED")){
                            orderItems.add(order.content.get(i));
                        }
                    }
                    if (orderItems.size() > 0){
                        linearLayout.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        Order_Diss_Adapter adapter = new Order_Diss_Adapter(Order_dis_Activity.this,orderItems);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Order_dis_Activity.this,RecyclerView.VERTICAL,false);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);
                    }else {
                        linearLayout.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }
                }}
            @Override
            public void onFailure(Call<ListOrder> call, Throwable t) {
                System.out.println("code order: ");
            }
        });
    }
    }
}