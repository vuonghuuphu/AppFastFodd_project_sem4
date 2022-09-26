package huuphu.aprotrain.client_app.View.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import huuphu.aprotrain.client_app.MainActivity;
import huuphu.aprotrain.client_app.Model.ListOrder;
import huuphu.aprotrain.client_app.Model.OrderDetails;
import huuphu.aprotrain.client_app.Model.OrderItem;
import huuphu.aprotrain.client_app.Network.ApiManager;
import huuphu.aprotrain.client_app.R;
import huuphu.aprotrain.client_app.View.Adapter.ListView.Order_Adapter;
import huuphu.aprotrain.client_app.View.Screen.CartActivity;
import huuphu.aprotrain.client_app.View.Screen.ScreenStartActivity;
import huuphu.aprotrain.client_app.data.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment_cart extends Fragment {

    RecyclerView recyclerView;
    TextView btn_to_nav_await,btn_to_nav_ship,btn_to_nav_ss;
    LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_cart, container, false);

    recyclerView = view.findViewById(R.id.rcv_order_list);
    btn_to_nav_await = view.findViewById(R.id.btn_to_nav_await);
    btn_to_nav_ship = view.findViewById(R.id.btn_to_nav_ship);
        btn_to_nav_ss = view.findViewById(R.id.btn_to_nav_ss);
        linearLayout = view.findViewById(R.id.ll_emty_order);

        btn_to_nav_await.setBackgroundColor(Color.parseColor("#F9B138"));
        btn_to_nav_await.setTextColor(Color.WHITE);
        btn_to_nav_ship.setBackgroundColor(Color.WHITE);
        btn_to_nav_ship.setTextColor(Color.BLACK);
        btn_to_nav_ss.setBackgroundColor(Color.WHITE);
        btn_to_nav_ss.setTextColor(Color.BLACK);

        if (Constants.token == null){
            Dialog dialog = new Dialog(view.getContext());
            dialog.setContentView(R.layout.dl_login);
            dialog.getWindow().setLayout(view.getLayoutParams().MATCH_PARENT,view.getLayoutParams().WRAP_CONTENT);
            dialog.show();
            Handler mHandler = new Handler();
            mHandler.postDelayed(() -> {
                Intent intent;
                intent = new Intent(view.getContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }, 2000);
        }else {
            ApiManager.getService().getOrder().enqueue(new Callback<ListOrder>() {
                @Override
                public void onResponse(Call<ListOrder> call, Response<ListOrder> response) {
                    ListOrder order = response.body();
                    List<OrderItem> orderItems = new ArrayList<>();
                    if (order.content != null) {
                        for (int i = 0; i < order.content.size(); i++) {
                            if (order.content.get(i).accountId.equals(Constants.idUser) && order.content.get(i).status.equals("WAITING")) {
                                orderItems.add(order.content.get(i));
                            }
                        }
                        if (orderItems.size() > 0) {
                            linearLayout.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            Order_Adapter adapter = new Order_Adapter(fragment_cart.this, orderItems);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                        } else {
                            linearLayout.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }
                    }
                }

                @Override
                public void onFailure(Call<ListOrder> call, Throwable t) {
                    System.out.println("code order: ");
                }
            });
        }

        btn_to_nav_await.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_to_nav_await.setBackgroundColor(Color.parseColor("#F9B138"));
                btn_to_nav_await.setTextColor(Color.WHITE);
                btn_to_nav_ship.setBackgroundColor(Color.WHITE);
                btn_to_nav_ship.setTextColor(Color.BLACK);
                btn_to_nav_ss.setBackgroundColor(Color.WHITE);
                btn_to_nav_ss.setTextColor(Color.BLACK);
                ApiManager.getService().getOrder().enqueue(new Callback<ListOrder>() {
                    @Override
                    public void onResponse(Call<ListOrder> call, Response<ListOrder> response) {
                        ListOrder order = response.body();
                        List<OrderItem> orderItems = new ArrayList<>();
                        if (order.content != null){
                            for (int i = 0; i < order.content.size(); i++) {
                                if (order.content.get(i).accountId.equals(Constants.idUser)&& order.content.get(i).status.equals("WAITING")){
                                    orderItems.add(order.content.get(i));
                                }
                            }   if (orderItems.size() > 0){
                                linearLayout.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                                Order_Adapter adapter = new Order_Adapter(fragment_cart.this,orderItems);
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false);
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
        });

        btn_to_nav_ship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_to_nav_ship.setBackgroundColor(Color.parseColor("#F9B138"));
                btn_to_nav_ship.setTextColor(Color.WHITE);
                btn_to_nav_await.setBackgroundColor(Color.WHITE);
                btn_to_nav_await.setTextColor(Color.BLACK);
                btn_to_nav_ss.setBackgroundColor(Color.WHITE);
                btn_to_nav_ss.setTextColor(Color.BLACK);
                ApiManager.getService().getOrder().enqueue(new Callback<ListOrder>() {
                    @Override
                    public void onResponse(Call<ListOrder> call, Response<ListOrder> response) {
                        ListOrder order = response.body();
                        List<OrderItem> orderItems = new ArrayList<>();
                        if (order.content != null){
                            for (int i = 0; i < order.content.size(); i++) {
                                if (order.content.get(i).accountId.equals(Constants.idUser) && order.content.get(i).status.equals("DELIVERING")){
                                    orderItems.add(order.content.get(i));
                                }
                            }
                            if (orderItems.size() > 0){
                                linearLayout.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                                Order_Adapter adapter = new Order_Adapter(fragment_cart.this,orderItems);
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false);
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
        });

        btn_to_nav_ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_to_nav_ss.setBackgroundColor(Color.parseColor("#F9B138"));
                btn_to_nav_ss.setTextColor(Color.WHITE);
                btn_to_nav_ship.setBackgroundColor(Color.WHITE);
                btn_to_nav_ship.setTextColor(Color.BLACK);
                btn_to_nav_await.setBackgroundColor(Color.WHITE);
                btn_to_nav_await.setTextColor(Color.BLACK);
                ApiManager.getService().getOrder().enqueue(new Callback<ListOrder>() {
                    @Override
                    public void onResponse(Call<ListOrder> call, Response<ListOrder> response) {
                        ListOrder order = response.body();
                        List<OrderItem> orderItems = new ArrayList<>();
                        if (order.content != null){
                            for (int i = 0; i < order.content.size(); i++) {
                                if (order.content.get(i).accountId.equals(Constants.idUser) && order.content.get(i).status.equals("CONFIRMED")){
                                    orderItems.add(order.content.get(i));
                                }
                            }
                            if (orderItems.size() > 0){
                                linearLayout.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                                Order_Adapter adapter = new Order_Adapter(fragment_cart.this,orderItems);
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false);
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
        });

    return view;
    }
}