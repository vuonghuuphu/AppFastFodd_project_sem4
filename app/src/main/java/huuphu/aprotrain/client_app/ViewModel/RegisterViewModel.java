package huuphu.aprotrain.client_app.ViewModel;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import huuphu.aprotrain.client_app.MainActivity;
import huuphu.aprotrain.client_app.Model.Customers;
import huuphu.aprotrain.client_app.Model.EnumApp;
import huuphu.aprotrain.client_app.Model.Request.Account_cus;
import huuphu.aprotrain.client_app.Model.Request.LoginRequest;
import huuphu.aprotrain.client_app.Model.Response.LoginResponse;
import huuphu.aprotrain.client_app.Model.Response.RegisterResponse;
import huuphu.aprotrain.client_app.Network.ApiManager;
import huuphu.aprotrain.client_app.R;
import huuphu.aprotrain.client_app.View.Screen.LoginActivity;
import huuphu.aprotrain.client_app.data.SharedPreferencesHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel {

    public static String Validate(String username,String password,String pass_com){
        if (username.isEmpty() && password.isEmpty()){
            return "Nhập thiếu tài khoản và mật khẩu";
        }if (username.isEmpty()){
            return "Nhập thiếu tài khoản";
        }if (password.isEmpty()){
            return "Nhập thiếu mật khẩu";
        }if(pass_com.isEmpty()){
            return "Nhập xác nhận mật khẩu";
        }if(!pass_com.equals(password)){
            return "Mật khẩu không khớp";
        }else {
            return null;
        }
    }

    public static void Register (LoginRequest request, View view, String pass_com, String Name ,String email , String phone) {
        final Dialog dialog = new Dialog(view.getContext()); // Context, this, etc.
        dialog.setContentView(R.layout.dialogloading);
        dialog.show();
        String validate = RegisterViewModel.
                Validate(
                        request.getUsername(),
                        request.getPassword(),
                        pass_com
                );
        if (validate != null) {
            dialog.cancel();
            Toast.makeText(view.getContext(), validate, Toast.LENGTH_SHORT).show();
        } else {
            System.out.println("hello");
            ApiManager.getService().apiRegister(request).enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(@NonNull Call<RegisterResponse> call, @NonNull Response<RegisterResponse> response) {
                    RegisterResponse res = response.body();
                    if (response.code() == 201){
                        Account_cus account_cus = new Account_cus(res.getId(),Name,null,email,null,phone);
                        System.out.println("okokok "+ res.getId());
                        ApiManager.getService().addCustomers(account_cus).enqueue(new Callback<Customers>() {
                            @Override
                            public void onResponse(Call<Customers> call, Response<Customers> response) {
                                if (response.code() == 201){
                        dialog.cancel();
                        Toast.makeText(view.getContext(), "Đăng kí tài khoản thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(view.getContext(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        view.getContext().startActivity(intent);
                                }
                            }

                            @Override
                            public void onFailure(Call<Customers> call, Throwable t) {

                            }
                        });

                    }else {
                        dialog.cancel();
                        Toast.makeText(view.getContext(), "Lỗi đăng kí tài khoản", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                }
            });
        }
    }

}