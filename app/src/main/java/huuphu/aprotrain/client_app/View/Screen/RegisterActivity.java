package huuphu.aprotrain.client_app.View.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

import huuphu.aprotrain.client_app.MainActivity;
import huuphu.aprotrain.client_app.Model.Request.Account_cus;
import huuphu.aprotrain.client_app.Model.Request.LoginRequest;
import huuphu.aprotrain.client_app.R;
import huuphu.aprotrain.client_app.ViewModel.LoginViewModel;
import huuphu.aprotrain.client_app.ViewModel.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity {

    Button btn_register ;
    EditText ed_username,ed_pass,ed_pass_confim,ed_Name_cus_res,ed_Phone_cus_res,ed_Email_cus_res;
    TextView tv_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide()  ;

        btn_register = findViewById(R.id.btn_res);
        ed_pass = findViewById(R.id.ed_pass_res);
        ed_username = findViewById(R.id.ed_username_res);
        tv_login = findViewById(R.id.tv_login);
        ed_pass_confim = findViewById(R.id.ed_passConfim_res);

        ed_Name_cus_res = findViewById(R.id.ed_Name_cus_res);
        ed_Phone_cus_res = findViewById(R.id.ed_Phone_cus_res);
        ed_Email_cus_res = findViewById(R.id.ed_Email_cus_res);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginRequest request = new LoginRequest(
                        ed_username.getText().toString(),
                        ed_pass.getText().toString()
                );
                RegisterViewModel.Register(
                        request,view,ed_pass_confim.getText().toString(),
                        ed_Name_cus_res.getText().toString(),
                        ed_Email_cus_res.getText().toString(),
                        ed_Phone_cus_res.getText().toString());
            }
        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                view.getContext().startActivity(intent);
            }
        });
    }
}