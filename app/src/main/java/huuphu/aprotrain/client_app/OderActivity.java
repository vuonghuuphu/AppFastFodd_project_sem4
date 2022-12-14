package huuphu.aprotrain.client_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import huuphu.aprotrain.client_app.Model.CartItem;
import huuphu.aprotrain.client_app.Model.Customers;
import huuphu.aprotrain.client_app.Model.EnumApp;
import huuphu.aprotrain.client_app.Model.OrderItem;
import huuphu.aprotrain.client_app.Model.Product;
import huuphu.aprotrain.client_app.Model.Request.Oder_res;
import huuphu.aprotrain.client_app.Model.Response.Account_cus_rp;
import huuphu.aprotrain.client_app.Model.Zalopay.CreateOrder;
import huuphu.aprotrain.client_app.Network.ApiManager;
import huuphu.aprotrain.client_app.View.Adapter.ListView.CartAdapter;
import huuphu.aprotrain.client_app.View.Adapter.ListView.Product_order_Adapter;
import huuphu.aprotrain.client_app.View.Screen.CartActivity;
import huuphu.aprotrain.client_app.View.Screen.ScreenStartActivity;
import huuphu.aprotrain.client_app.data.Constants;
import huuphu.aprotrain.client_app.data.SharedPreferencesHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.momo.momo_partner.AppMoMoLib;
import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class OderActivity extends AppCompatActivity {

    TextView btn_thaypttt,tv_pttt,ttprice_oder,giadonhang,tv_thue,tv_edit_cus,order_name_cus,order_address_cus,order_sdt_cus;
    Button btn_buy;
    String checkpay = EnumApp.Money;
    RecyclerView recyclerView;
    ImageView back;
    EditText ed_name_cus,ed_address_cus,ed_sdt_res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oder);
        ZaloPaySDK.init(2554, Environment.SANDBOX);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        StrictMode.ThreadPolicy policy = new
        StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Dialog dialog = new Dialog(OderActivity.this);

        AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.DEVELOPMENT);

        recyclerView = findViewById(R.id.rcv_product_order);
        order_name_cus = findViewById(R.id.order_name_cus);
        order_address_cus = findViewById(R.id.order_address_cus);
        order_sdt_cus = findViewById(R.id.order_sdt_cus);
        btn_buy = findViewById(R.id.button_buy);
        back = findViewById(R.id.back_oder);
        ttprice_oder = findViewById(R.id.ttprice_oder);
        tv_edit_cus = findViewById(R.id.tv_edit_cus);

        Constants.name_cus = SharedPreferencesHelper.ReadUser(EnumApp.KeyName_cus,this);
        Constants.sdt_cus = SharedPreferencesHelper.ReadUser(EnumApp.KeyPhone_cus,this);
        Constants.address_cus = SharedPreferencesHelper.ReadUser(EnumApp.Keyaddress_cus,this);
        order_name_cus.setText("T??n kh??ch h??ng: "+Constants.name_cus);
        order_sdt_cus.setText("S??? ??i???n tho???i: "+Constants.sdt_cus);
        order_address_cus.setText("?????a ch??? nh???n h??ng: "+Constants.address_cus);

        double thue = Constants.ttp / 10;
        String Pricett = NumberFormat.getNumberInstance(Locale.US).format(Constants.ttp);
        ttprice_oder.setText(Pricett+" vn??");

        int t = (int) (Constants.ttp + 20000 + thue);
        Constants.TotalPrice = ""+t;

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tv_edit_cus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.dl_edit_account_in_order);
                dialog.getWindow().setLayout(view.getLayoutParams().MATCH_PARENT,view.getLayoutParams().WRAP_CONTENT);
                dialog.setCancelable(true);

                TextView dialogedit_cus,
                        ed_name_cus_dl,
                        ed_address_cus_dl,
                        ed_sdt_cus_dl,
                        ed_email_cus_dl,
                        ed_gender_cus_dl;


                dialogedit_cus = dialog.findViewById(R.id.dialogedit_cus);
                ed_address_cus_dl = dialog.findViewById(R.id.ed_address_cus_dl);
                ed_name_cus_dl = dialog.findViewById(R.id.ed_name_cus_dl);
                ed_sdt_cus_dl = dialog.findViewById(R.id.ed_sdt_cus_dl);
                ed_email_cus_dl = dialog.findViewById(R.id.ed_email_cus_dl);
                ed_gender_cus_dl = dialog.findViewById(R.id.ed_gender_cus_dl);

                ApiManager.getService().getCustomers().enqueue(new Callback<List<Customers>>() {
                    @Override
                    public void onResponse(Call<List<Customers>> call, Response<List<Customers>> response) {
                        List<Customers> customers = response.body();
                        for (int i = 0; i < customers.size(); i++) {
                            if (customers.get(i).id_account.equals(Constants.idUser)){
                                ed_name_cus_dl.setText(customers.get(i).getName());
                                ed_address_cus_dl.setText(customers.get(i).getAddress());
                                ed_email_cus_dl.setText(customers.get(i).getEmail());
                                if (customers.get(i).getGender() == null){
                                    ed_gender_cus_dl.setText(" Ch??a c?? th??ng tin* ");
                                }else {
                                    ed_gender_cus_dl.setText(customers.get(i).getGender());
                                }
                                ed_sdt_cus_dl.setText( customers.get(i).phone_number);
                                int idr = customers.get(i).getId();
                                String Id_account = customers.get(i).getId_account();
                dialogedit_cus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(ed_name_cus_dl.getText().toString().equals("") ||
                                ed_address_cus_dl.getText().toString().equals("") ||
                                ed_sdt_cus_dl.getText().toString().equals("")){
                            Toast.makeText(OderActivity.this, "Nh???p ?????y ????? c??c th??ng tin", Toast.LENGTH_SHORT).show();
                        }else {
                            Account_cus_rp account_cus_rp = new Account_cus_rp(
                                    idr,
                                    Id_account,
                                    ed_name_cus_dl.getText().toString(),
                                    ed_gender_cus_dl.getText().toString(),
                                    ed_email_cus_dl.getText().toString(),
                                    ed_address_cus_dl.getText().toString(),
                                    ed_sdt_cus_dl.getText().toString());
                            ApiManager.getService().EditCustomers(""+idr,account_cus_rp) .enqueue(new Callback<Account_cus_rp>() {
                                @Override
                                public void onResponse(Call<Account_cus_rp> call, Response<Account_cus_rp> response) {
                                    Constants.name_cus = ed_name_cus_dl.getText().toString();
                                    Constants.sdt_cus = ed_address_cus_dl.getText().toString();
                                    Constants.address_cus =  ed_sdt_cus_dl.getText().toString();
                                    dialog.cancel();
                                }

                                @Override
                                public void onFailure(Call<Account_cus_rp> call, Throwable t) {

                                }
                            });

                            Constants.name_cus = ed_name_cus_dl.getText().toString();
                            Constants.sdt_cus = ed_address_cus_dl.getText().toString();
                            Constants.address_cus =  ed_sdt_cus_dl.getText().toString();
                            order_name_cus.setText("T??n kh??ch h??ng: "+ed_name_cus_dl.getText().toString());
                            order_sdt_cus.setText("S??? ??i???n tho???i: "+ed_address_cus_dl.getText().toString());
                            order_address_cus.setText("?????a ch??? nh???n h??ng: "+ed_sdt_cus_dl.getText().toString());
                            dialog.cancel();
                        }

                    }
                });
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Customers>> call, Throwable t) {

                    }
                });
                dialog.show();
            }
        });

        Product_order_Adapter cartAdapter = new Product_order_Adapter(this,Constants.list_oder_product);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(cartAdapter);

//        zalo pay
            btn_buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Dialog dialog = new Dialog(view.getContext()); // Context, this, etc.
                    dialog.setContentView(R.layout.dialogloading);
                    dialog.show();
                    if (checkpay.equals(EnumApp.Zalo)){
                        System.out.println("pay app"+ "ZaloPay");
                    CreateOrder orderApi = new CreateOrder();
                    try {
                        JSONObject data = orderApi.createOrder(Constants.TotalPrice);
                        String code = data.getString("return_code");
                        System.out.println("pay app"+ code);
                        if (code.equals("1")) {
                            String Token = data.getString("zp_trans_token");
                            System.out.println("pay app"+ Token);

                            ZaloPaySDK.getInstance().payOrder(OderActivity.this, Token, "appfood://app", new PayOrderListener() {
                                @Override
                                public void onPaymentSucceeded(final String transactionId, final String transToken, final String appTransID) {
                                    Oder_res oder_res = new Oder_res(Constants.idUser,Constants.name_cus,Constants.address_cus,Constants.sdt_cus,"Mua h??ng","v?? ZaloPay");
                                    ApiManager.getService().AddOrder(oder_res).enqueue(new Callback<OrderItem>() {
                                        @Override
                                        public void onResponse(Call<OrderItem> call, Response<OrderItem> response) {
                                            System.out.println("Order_App = " + response.code());
                                            for (int i = 0 ;i < Constants.list_oder_product.size();i++){
                                                ApiManager.getService().DeleteCart(Constants.list_oder_product.get(i).getId()).enqueue(new Callback<Boolean>() {
                                                    @Override
                                                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                                    }

                                                    @Override
                                                    public void onFailure(Call<Boolean> call, Throwable t) {
                                                    }
                                                });
                                            }

                                            Intent intent = new Intent(OderActivity.this, thanhcongActivity.class);
                                            startActivity(intent);
                                        }

                                        @Override
                                        public void onFailure(Call<OrderItem> call, Throwable t) {

                                        }
                                    });
                                 }
                                @Override
                                public void onPaymentCanceled(String zpTransToken, String appTransID) {
                                    dialog.cancel();
                                    Intent intent = new Intent(OderActivity.this, ThatBaiActivity.class);
                                    startActivity(intent);
                                }
                                @Override
                                public void onPaymentError(ZaloPayError zaloPayError, String zpTransToken, String appTransID) {
                                    dialog.cancel();
                                    Intent intent = new Intent(OderActivity.this, ThatBaiActivity.class);
                                    startActivity(intent);
                                }
                            });
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    }
                    if (checkpay.equals(EnumApp.Money)){
                        dialog.cancel();
                        Oder_res oder_res = new Oder_res(Constants.idUser,Constants.name_cus,Constants.address_cus,Constants.sdt_cus,"Mua h??ng","Ti???n m???t");
                        ApiManager.getService().AddOrder(oder_res).enqueue(new Callback<OrderItem>() {
                            @Override
                            public void onResponse(Call<OrderItem> call, Response<OrderItem> response) {
                                System.out.println("Order_App = " + response.code());
                                for (int i = 0 ;i < Constants.list_oder_product.size();i++){
                                    ApiManager.getService().DeleteCart(Constants.list_oder_product.get(i).getId()).enqueue(new Callback<Boolean>() {
                                        @Override
                                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                        }

                                        @Override
                                        public void onFailure(Call<Boolean> call, Throwable t) {
                                        }
                                    });
                                }

                                Intent intent = new Intent(OderActivity.this, thanhcongActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<OrderItem> call, Throwable t) {

                            }
                        });
                    }

                    if (checkpay.equals(EnumApp.Momo)){

                        String amount = ""+Constants.ttp;
                        String fee = "0";
                        int environment = 0;//developer default
                        String merchantName = "FastFood";
                        String merchantCode = "MOMO2YM020220921";
                        String merchantNameLabel = "Nh?? cung c???p";
                        String description = "Thanh to??n ????n h??ng ????? ??n ";

                        AppMoMoLib.getInstance().setAction(AppMoMoLib.ACTION.PAYMENT);
                        AppMoMoLib.getInstance().setActionType(AppMoMoLib.ACTION_TYPE.GET_TOKEN);
                        Map<String, Object> eventValue = new HashMap<>();
                        eventValue.put("merchantname", merchantName); //T??n ?????i t??c. ???????c ????ng k?? t???i https://business.momo.vn. VD: Google, Apple, Tiki , CGV Cinemas
                        eventValue.put("merchantcode", merchantCode); //M?? ?????i t??c, ???????c cung c???p b???i MoMo t???i https://business.momo.vn
                        eventValue.put("amount", amount); //Ki???u integer
                        eventValue.put("orderId", " m?? ????n h??ng: 8378234788234"); //uniqueue id cho Bill order, gi?? tr??? duy nh???t cho m???i ????n h??ng
                        eventValue.put("orderLabel", "M?? ????n h??ng"); //g??n nh??n

                        //client Optional - bill info
                        eventValue.put("merchantnamelabel", "D???ch v???");//g??n nh??n
                        eventValue.put("fee", 0); //Ki???u integer
                        eventValue.put("description", description); //m?? t??? ????n h??ng - short description

                        //client extra data
                        eventValue.put("requestId",  merchantCode+"merchant_billId_"+System.currentTimeMillis());
                        eventValue.put("partnerCode", merchantCode);
                        JSONObject objExtraData = new JSONObject();
                        try {
                            objExtraData.put("site_code", "008");
                            objExtraData.put("site_name", "CGV Cresent Mall");
                            objExtraData.put("screen_code", 0);
                            objExtraData.put("screen_name", "Special");
                            objExtraData.put("movie_name", "K??? Tr???m M???t Tr??ng 3");
                            objExtraData.put("movie_format", "2D");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        eventValue.put("extraData", objExtraData.toString());
//                        Intent intent = new Intent(OderActivity.this, thanhcongActivity.class);
//                        onActivityResult(0,0,intent);

                        eventValue.put("extra", "");
                        AppMoMoLib.getInstance().requestMoMoCallBack(OderActivity.this, eventValue);
                    }

                }
            });
//


        btn_thaypttt = findViewById(R.id.tv_load_pttt);
        tv_pttt = findViewById(R.id.tv_pttt);
        ImageView img_ic_pay = findViewById(R.id.ic_pay);

        btn_thaypttt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.dl_select_pay);
                dialog.getWindow().setLayout(view.getLayoutParams().MATCH_PARENT,view.getLayoutParams().WRAP_CONTENT);
                dialog.setCancelable(false);
                TextView btnClose = dialog.findViewById(R.id.dialogclose);
                btnClose.setOnClickListener(view1 -> dialog.cancel());

                LinearLayout tv_money,tv_zalo,tv_momo;
                tv_money = dialog.findViewById(R.id.tv_money);
                tv_zalo = dialog.findViewById(R.id.tv_zalopay);
                tv_momo = dialog.findViewById(R.id.tv_momo);

                tv_momo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkpay = EnumApp.Momo;
                        tv_pttt.setText("Thanh to??n b???ng v?? Momo");
                        img_ic_pay.setImageResource(R.drawable.logomomo);
                        dialog.cancel();
                    }
                });

                tv_money.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkpay = EnumApp.Money;
                        tv_pttt.setText("Thanh to??n khi nh???n h??ng");
                        img_ic_pay.setImageResource(R.drawable.money);
                        dialog.cancel();
                    }
                });

                tv_zalo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkpay = EnumApp.Zalo;
                        tv_pttt.setText("Thanh to??n b???ng v?? Zalopay");
                        img_ic_pay.setImageResource(R.drawable.zalopayngang);
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == AppMoMoLib.getInstance().REQUEST_CODE_MOMO && resultCode == -1) {
            if(data != null) {
                if(data.getIntExtra("status", -1) == 0) {
                    System.out.println("Payment Momo " + "ok");
                    Oder_res oder_res = new Oder_res(Constants.idUser,Constants.name_cus,Constants.address_cus,Constants.sdt_cus,"Mua h??ng","v?? Momo");
                    ApiManager.getService().AddOrder(oder_res).enqueue(new Callback<OrderItem>() {
                        @Override
                        public void onResponse(Call<OrderItem> call, Response<OrderItem> response) {
                            System.out.println("Order_App = " + response.code());
                            for (int i = 0 ;i < Constants.list_oder_product.size();i++){
                                ApiManager.getService().DeleteCart(Constants.list_oder_product.get(i).getId()).enqueue(new Callback<Boolean>() {
                                    @Override
                                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                    }

                                    @Override
                                    public void onFailure(Call<Boolean> call, Throwable t) {
                                    }
                                });
                            }

                            Intent intent = new Intent(OderActivity.this, thanhcongActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<OrderItem> call, Throwable t) {

                        }
                    });
                    Intent intent = new Intent(OderActivity.this, thanhcongActivity.class);
                    startActivity(intent);
            } else {
                    System.out.println("Payment Momo " + "f");
                    Intent intent = new Intent(OderActivity.this, ThatBaiActivity.class);
                    startActivity(intent);
            }
        } else {
                System.out.println("Payment Momo " + "f");
                Intent intent = new Intent(OderActivity.this, ThatBaiActivity.class);
                startActivity(intent);
        }
    }
}

}