package huuphu.aprotrain.client_app.ViewModel;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import huuphu.aprotrain.client_app.MainActivity;
import huuphu.aprotrain.client_app.Model.EnumApp;
import huuphu.aprotrain.client_app.Model.Request.LoginRequest;
import huuphu.aprotrain.client_app.Model.Response.LoginResponse;
import huuphu.aprotrain.client_app.Model.User;
import huuphu.aprotrain.client_app.Model.UserRes;
import huuphu.aprotrain.client_app.Network.ApiManager;
import huuphu.aprotrain.client_app.R;
import huuphu.aprotrain.client_app.data.Constants;
import huuphu.aprotrain.client_app.data.SharedPreferencesHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel {

    public static String Validate(String username,String password){
        if (username.isEmpty() && password.isEmpty()){
            return "Nhập thiếu tài khoản và mật khẩu";
        }if (username.isEmpty()){
            return "Nhập thiếu tài khoản";
        }if (password.isEmpty()){
            return "Nhập thiếu mật khẩu";
        }else {
            return null;
        }
    }

    public static void Login(LoginRequest request, View view) {
        final Dialog dialog = new Dialog(view.getContext()); // Context, this, etc.
        dialog.setContentView(R.layout.dialogloading);
        dialog.show();
        String validate = LoginViewModel.
                Validate(
                        request.getUsername(),
                        request.getPassword()
                );
        if (validate != null) {
            Toast.makeText(view.getContext(), validate, Toast.LENGTH_SHORT).show();
        } else {
            ApiManager.getService().apiLogin(request).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                    LoginResponse res = response.body();
                    if (response.code() == 200 ){
                        assert res != null;
                        String token = res.getAccessToken();
                    if (!token.equals("")) {
                        System.out.println("tokent"+token);
                        SharedPreferencesHelper.Save(EnumApp.KeyToken,token,view);
                        Constants.token = SharedPreferencesHelper.ReadUser(EnumApp.KeyToken,view.getContext());
                        ApiManager.getService().getUser().enqueue(new Callback<UserRes>() {
                            @Override
                            public void onResponse(Call<UserRes> call, Response<UserRes> response) {
                                UserRes user = response.body();
                                SharedPreferencesHelper.Save(EnumApp.KeyIdUser,user.getUser().id,view);
                                SharedPreferencesHelper.Save(EnumApp.KeyNameUser,user.getUser().username,view);
                                Constants.idUser = SharedPreferencesHelper.ReadUser(EnumApp.KeyIdUser,view.getContext());
                                Constants.name_cus = SharedPreferencesHelper.ReadUser(EnumApp.KeyNameUser,view.getContext());
                            }

                            @Override
                            public void onFailure(Call<UserRes> call, Throwable t) {

                            }
                        });
                        dialog.cancel();
                        Intent intent = new Intent(view.getContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        view.getContext().startActivity(intent);
                    } else {
                        dialog.cancel();
                        Toast.makeText(view.getContext(), "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                    }else {
                        dialog.cancel();
                        Toast.makeText(view.getContext(), "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                }
            });
        }
    }

}
