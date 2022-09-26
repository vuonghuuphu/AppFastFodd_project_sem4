package huuphu.aprotrain.client_app.View.Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
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

import com.google.gson.Gson;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import huuphu.aprotrain.client_app.MainActivity;
import huuphu.aprotrain.client_app.Model.Account;
import huuphu.aprotrain.client_app.Model.Cart;
import huuphu.aprotrain.client_app.Model.CartItem;
import huuphu.aprotrain.client_app.Model.Customers;
import huuphu.aprotrain.client_app.Model.EnumApp;
import huuphu.aprotrain.client_app.Model.ListOrder;
import huuphu.aprotrain.client_app.Model.OrderItem;
import huuphu.aprotrain.client_app.Model.Request.Account_cus;
import huuphu.aprotrain.client_app.Model.Response.Account_cus_rp;
import huuphu.aprotrain.client_app.Network.ApiManager;
import huuphu.aprotrain.client_app.OderActivity;
import huuphu.aprotrain.client_app.R;
import huuphu.aprotrain.client_app.View.Adapter.ListView.CartAdapter;
import huuphu.aprotrain.client_app.View.Adapter.ListView.Order_Adapter;
import huuphu.aprotrain.client_app.View.Fragment.fragment_cart;
import huuphu.aprotrain.client_app.View.Onclick.CartListener;
import huuphu.aprotrain.client_app.View.Onclick.ShoppingCartOnclick;
import huuphu.aprotrain.client_app.data.Constants;
import huuphu.aprotrain.client_app.data.SharedPreferencesHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity implements CartListener {

    ImageView btn_back;
    RecyclerView rcv ;
    TextView TTPrice;
    Button button_oder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        btn_back= findViewById(R.id.btn_back_cart);
        rcv = findViewById(R.id.rcv_listCart);
        TTPrice = findViewById(R.id.ttprice);
        button_oder = findViewById(R.id.button_oder_app);

        Constants.list_oder_product = null;
        Constants.ttp = 0.0;

        button_oder.setOnClickListener(view -> {
            Constants.name_cus = SharedPreferencesHelper.ReadUser(EnumApp.KeyName_cus,this);
            Constants.sdt_cus = SharedPreferencesHelper.ReadUser(EnumApp.KeyPhone_cus,this);
            Constants.address_cus = SharedPreferencesHelper.ReadUser(EnumApp.Keyaddress_cus,this);
            if(Constants.ttp == 0){
                Toast.makeText(this, "Chưa chọn sản phẩm nào !", Toast.LENGTH_SHORT).show();
            }else {
                if (Constants.name_cus != null&&Constants.sdt_cus!= null&&Constants.address_cus!=null){
                    Intent intent = new Intent(CartActivity.this, OderActivity.class);
                    startActivity(intent);
                }else{
                    Dialog dialog = new Dialog(CartActivity.this);
                    dialog.setContentView(R.layout.dl_edit_cus);
                    dialog.getWindow().setLayout(view.getLayoutParams().MATCH_PARENT,view.getLayoutParams().WRAP_CONTENT);
                    dialog.setCancelable(false);
                    TextView btnClose = dialog.findViewById(R.id.dialogclose_cus);
                    TextView btnadd = dialog.findViewById(R.id.dialogedit_cus);

                    EditText ed_address_cus = dialog.findViewById(R.id.ed_address_cus);

                    btnClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ApiManager.getService().getCustomers().enqueue(new Callback<List<Customers>>() {
                                @Override
                                public void onResponse(Call<List<Customers>> call, Response<List<Customers>> response) {
                                    List<Customers> customers = response.body();
                                    for (int i = 0; i < customers.size(); i++) {
                                        if (Objects.equals(customers.get(i).getId_account(), Constants.idUser)){
                                            SharedPreferencesHelper.Save1(EnumApp.KeyName_cus,customers.get(i).getName(), CartActivity.this);
                                            SharedPreferencesHelper.Save1(EnumApp.Keyaddress_cus,customers.get(i).getAddress(),CartActivity.this);
                                            SharedPreferencesHelper.Save1(EnumApp.KeyPhone_cus,customers.get(i).getPhone_number(),CartActivity.this);
                                            customers.get(i).setAddress(ed_address_cus.getText().toString());
                                            SharedPreferencesHelper.Save1(EnumApp.Keyaddress_cus,ed_address_cus.getText().toString(),CartActivity.this);
                                            Account_cus_rp account_cus_rp = new Account_cus_rp(
                                                    customers.get(i).getId(),
                                                    customers.get(i).getId_account(),
                                                    customers.get(i).getName(),
                                                    customers.get(i).getGender(),
                                                    customers.get(i).getEmail(),
                                                    customers.get(i).getAddress(),
                                                    customers.get(i).getPhone_number());
                                            Gson gson = new Gson();
                                            int idr = customers.get(i).getId();
                                            ApiManager.getService().EditCustomers(""+idr,account_cus_rp) .enqueue(new Callback<Account_cus_rp>() {
                                                @Override
                                                public void onResponse(Call<Account_cus_rp> call, Response<Account_cus_rp> response) {
                                                    Constants.name_cus = SharedPreferencesHelper.ReadUser(EnumApp.KeyName_cus,CartActivity.this);
                                                    Constants.sdt_cus = SharedPreferencesHelper.ReadUser(EnumApp.KeyPhone_cus,CartActivity.this);
                                                    Constants.address_cus = SharedPreferencesHelper.ReadUser(EnumApp.Keyaddress_cus,CartActivity.this);
                                                    dialog.cancel();
                                                }

                                                @Override
                                                public void onFailure(Call<Account_cus_rp> call, Throwable t) {

                                                }
                                            });
                                        }
                                    }
                                    Constants.name_cus = SharedPreferencesHelper.ReadUser(EnumApp.KeyName_cus,CartActivity.this);
                                    Constants.sdt_cus = SharedPreferencesHelper.ReadUser(EnumApp.KeyPhone_cus,CartActivity.this);
                                    Constants.address_cus = SharedPreferencesHelper.ReadUser(EnumApp.Keyaddress_cus,CartActivity.this);
                                }

                                @Override
                                public void onFailure(Call<List<Customers>> call, Throwable t) {

                                }
                            });
                        }
                    });

                    btnadd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(
                               ed_address_cus.getText().toString().equals("")){
                                Toast.makeText(CartActivity.this, "Nhập đầy đủ các thông tin", Toast.LENGTH_SHORT).show();
                            }else {
//                                SharedPreferencesHelper.Save(EnumApp.KeyName_cus,ed_name_cus.getText().toString(),view);
                                SharedPreferencesHelper.Save(EnumApp.Keyaddress_cus,ed_address_cus.getText().toString(),view);
//                                SharedPreferencesHelper.Save(EnumApp.KeyPhone_cus,ed_sdt_res.getText().toString(),view);
                                Intent intent = new Intent(CartActivity.this, OderActivity.class);
                                startActivity(intent);
                                dialog.cancel();
                            }

                        }
                    });
                    dialog.show();
                }

            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Constants.ttp = 0.0;
        if (Constants.token == null){
            Dialog dialog = new Dialog(CartActivity.this);
            dialog.setContentView(R.layout.dl_login);
            dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            dialog.show();
            Handler mHandler = new Handler();
            mHandler.postDelayed(() -> {
                Intent intent;
                intent = new Intent(CartActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }, 2000);
        }else {
            ApiManager.getService().getCart().enqueue(new Callback<List<Cart>>() {
                @Override
                public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                    List<Cart> cartList = response.body();
                    List<CartItem> items = new ArrayList<>();
                    String IdAccount = Constants.idUser;
                    System.out.println("pay app"+Constants.idUser);
                    System.out.println("Call api = "+ cartList.get(0).getAccountId());
                    for (int i = 0 ; i< cartList.size();i++){
                        if (Objects.equals(cartList.get(i).getAccountId(), IdAccount)){
                            for (int j =0; j < cartList.get(i).getCartItems().size();j++){
                                items.add(cartList.get(i).getCartItems().get(j));
                                CartAdapter cartAdapter = new CartAdapter(CartActivity.this,cartList.get(i).getCartItems(),CartActivity.this);
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CartActivity.this,RecyclerView.VERTICAL,false);
                                Constants.list_oder_product = cartList.get(i).getCartItems();
                                rcv.setLayoutManager(layoutManager);
                                rcv.setAdapter(cartAdapter);
                                cartAdapter.DeleteOnclick(position -> ApiManager.getService().DeleteCart(items.get(position).getId()).enqueue(new Callback<Boolean>() {
                                    @Override
                                    public void onResponse(Call<Boolean> call1, Response<Boolean> response1) {
                                        items.remove((items.get(position)));
                                        Toast.makeText(CartActivity.this,"Delete", Toast.LENGTH_SHORT).show();
                                        CartAdapter cartAdapter1 = new CartAdapter(CartActivity.this,items,CartActivity.this);
                                        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(CartActivity.this,RecyclerView.VERTICAL,false);
                                        Constants.list_oder_product = items;
                                        rcv.setLayoutManager(layoutManager1);
                                        rcv.setAdapter(cartAdapter1);
                                        Constants.ttp = 0;
                                        for (CartItem cartitem : items) {
                                            Constants.ttp = Constants.ttp + (cartitem.getUnitPrice()*cartitem.getQuantity());
                                        }
                                        String Price = NumberFormat.getNumberInstance(Locale.US).format(Constants.ttp);
                                        TTPrice.setText(Price+" vnđ");
                                    }
                                    @Override
                                    public void onFailure(Call<Boolean> call1, Throwable t) {

                                    }
                                }));
                            }
                            System.out.println("Cảtyyyyy" + items.size());
                        }
                        Constants.ttp = 0;
                        for (CartItem cartitem : items) {
                            Constants.ttp = Constants.ttp + (cartitem.getUnitPrice()*cartitem.getQuantity());
                        }
                    }
                    String Price = NumberFormat.getNumberInstance(Locale.US).format(Constants.ttp);
                    TTPrice.setText(Price+" vnđ");



                }

                @Override
                public void onFailure(Call<List<Cart>> call, Throwable t) {
                }
            });
        }

    }

    @Override
    public void onliscart(ArrayList<CartItem> arrayList) {
        if (arrayList == null){
            System.out.println("Erro Cart");
        }else {
            int t = 0;
            for (CartItem items : arrayList) {
                System.out.println("Erro Cart");
            }
        }
    }

    public void updateTextView (String toThis) {
        TextView textView = (TextView) findViewById(R.id.ttprice);
        textView.setText(toThis);
    }

}