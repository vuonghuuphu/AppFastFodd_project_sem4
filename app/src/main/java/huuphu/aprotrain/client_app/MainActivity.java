package huuphu.aprotrain.client_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.navigation.NavigationView;

import java.util.List;
import java.util.Objects;

import huuphu.aprotrain.client_app.Model.Customers;
import huuphu.aprotrain.client_app.Model.EnumApp;
import huuphu.aprotrain.client_app.Model.User;
import huuphu.aprotrain.client_app.Model.UserRes;
import huuphu.aprotrain.client_app.Network.ApiManager;
import huuphu.aprotrain.client_app.View.Fragment.fragment_cart;
import huuphu.aprotrain.client_app.View.Fragment.fragment_home;
import huuphu.aprotrain.client_app.View.Fragment.fragment_news;
import huuphu.aprotrain.client_app.View.Fragment.fragment_sale;
import huuphu.aprotrain.client_app.View.Fragment.fragment_user;
import huuphu.aprotrain.client_app.View.Screen.CartActivity;
import huuphu.aprotrain.client_app.View.Screen.ListCategoriesActivity;
import huuphu.aprotrain.client_app.View.Screen.LoginActivity;
import huuphu.aprotrain.client_app.View.Screen.ScreenStartActivity;
import huuphu.aprotrain.client_app.data.Constants;
import huuphu.aprotrain.client_app.data.SharedPreferencesHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ImageView btn_leftmenu;
    NavigationView navigationView;
    MeowBottomNavigation meowBottomNavigation;
    ImageView btn_Cart,btn_search;
    TextView btn_menu,tv_username,tv_point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide()  ;

        Intent getintent = getIntent();
        int navigation = getintent.getIntExtra("navigation",0);


        drawerLayout = findViewById(R.id.drawer_layout);
        btn_leftmenu = findViewById(R.id.btn_leftmenu);
        btn_Cart = findViewById(R.id.btn_cart);
        navigationView = findViewById(R.id.navigationview);
        View headerview = navigationView.getHeaderView(0);
        btn_menu = headerview.findViewById(R.id.text_btn_menu);
        tv_username = headerview.findViewById(R.id.tv_item_header_name);
        btn_search = findViewById(R.id.btn_search);

        Constants.idUser = "";
        Constants.name_cus = "";
        Constants.idUser = SharedPreferencesHelper.ReadUser(EnumApp.KeyIdUser,this);
//        String name = SharedPreferencesHelper.ReadUser(EnumApp.KeyNameUser,this);

        ApiManager.getService().getCustomers().enqueue(new Callback<List<Customers>>() {
            @Override
            public void onResponse(Call<List<Customers>> call, Response<List<Customers>> response) {
                List<Customers> list = response.body();
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        if (Objects.equals(Constants.idUser, list.get(i).getId_account())) {
                            Constants.name_cus = list.get(i).name;
                        }
                    }
                }
                if (Constants.token == null ){
                    tv_username.setText("Tài khoản khách");
                    btn_menu.setText("Đăng nhập");
                    btn_menu.setBackgroundResource(R.color.Color_F9B138);
                }else {
                    tv_username.setText(Constants.name_cus);
                    btn_menu.setText("Đăng xuất");
                    btn_menu.setBackgroundResource(R.color.red);
                }
                btn_menu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (btn_menu.getText().toString() == "Đăng nhập" ){
                            System.out.println("id_usser = n");
                            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }else{
                            System.out.println("id_usser = y");
                            Constants.idUser = null;
                            Constants.token = null;
                            Constants.name_cus = null;
                            SharedPreferencesHelper.delete(EnumApp.KeyToken,view.getContext());
                            SharedPreferencesHelper.delete(EnumApp.KeyIdUser,view.getContext());
                            SharedPreferencesHelper.delete(EnumApp.KeyNameUser,view.getContext());
                            ReplaceScreen();
                            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Customers>> call, Throwable t) {

            }
        });


        ApiManager.getService().getCustomers().enqueue(new Callback<List<Customers>>() {
            @Override
            public void onResponse(Call<List<Customers>> call, Response<List<Customers>> response) {
                List<Customers> customers = response.body();
                Customers customers1 = new Customers();
                for (int i = 0; i < customers.size(); i++) {
                    System.out.println("localstorage app "+ customers.get(i).id_account + " " + Constants.idUser);
                    if (Objects.equals(customers.get(i).getId_account(), Constants.idUser)){
                        SharedPreferencesHelper.Save1(EnumApp.KeyName_cus,customers.get(i).getName(),MainActivity.this);
                        SharedPreferencesHelper.Save1(EnumApp.Keyaddress_cus,customers.get(i).getAddress(),MainActivity.this);
                        SharedPreferencesHelper.Save1(EnumApp.KeyPhone_cus,customers.get(i).getPhone_number(),MainActivity.this);
                    }
                }
                Constants.name_cus = SharedPreferencesHelper.ReadUser(EnumApp.KeyName_cus,MainActivity.this);
                Constants.sdt_cus = SharedPreferencesHelper.ReadUser(EnumApp.KeyPhone_cus,MainActivity.this);
                Constants.address_cus = SharedPreferencesHelper.ReadUser(EnumApp.Keyaddress_cus,MainActivity.this);
                System.out.println("localstorage app "+ Constants.name_cus +" " + Constants.sdt_cus + " "+Constants.address_cus);
            }

            @Override
            public void onFailure(Call<List<Customers>> call, Throwable t) {

            }
        });

        btn_Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CartActivity.class);
                startActivity(intent);
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });


        btn_leftmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView = findViewById(R.id.navigationview);
        navigationView.setItemIconTintList(null);
        meowBottomNavigation = findViewById(R.id.btm_nav);
        meowBottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_home));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_sale));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_bill));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.ic_accounts));
        meowBottomNavigation.setOnReselectListener((MeowBottomNavigation.ReselectListener) model -> {

        });

        meowBottomNavigation.setOnClickMenuListener((MeowBottomNavigation.ClickListener) model -> {
           switch (model.getId()){
               case 1 :
                   replace(new fragment_home());
                   break;
               case 2 :
                   replace(new fragment_sale());
                   break;
               case 3 :
                   replace(new fragment_cart());
                   break;
               case 4 :
                   replace(new fragment_user());
                   break;

           }
        });
        meowBottomNavigation.setOnShowListener((MeowBottomNavigation.ShowListener) model -> {
        });
        replace(new fragment_home());
        if (navigation == 3){
            meowBottomNavigation.show(navigation,true);
            replace(new fragment_cart());
        }
        else {
            meowBottomNavigation.show(1, true);
        }
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void replace(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }

    private void ReplaceScreen(){
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        startActivity(intent);
        this.finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItem.setChecked(true);
        switch (menuItem.getItemId()) {
            case R.id.menuCategory:
                Intent intent = new Intent(this, ListCategoriesActivity.class);
                startActivity(intent);
                drawerLayout.close();
                break;
            case R.id.menuCart:
                Intent intentcart = new Intent(this, CartActivity.class);
                startActivity(intentcart);
                drawerLayout.close();
                break;
            case R.id.menuBill:
                meowBottomNavigation.show(3,true);
                replace(new fragment_cart());
                drawerLayout.close();
                break;
            case R.id.menuPoint:
                // do you click actions for the third selection
                break;
            case R.id.menuNotification:
                // do you click actions for the third selection
                break;
            case R.id.menuHelp:
                // do you click actions for the third selection
                break;
            case R.id.menuFeedback:
                // do you click actions for the third selection
                break;
            case R.id.menuSetting:
                // do you click actions for the third selection
                break;
        }
        return true;
    }
}