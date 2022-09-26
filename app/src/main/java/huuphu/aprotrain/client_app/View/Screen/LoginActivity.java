package huuphu.aprotrain.client_app.View.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Objects;
import huuphu.aprotrain.client_app.Model.Request.LoginRequest;
import huuphu.aprotrain.client_app.R;
import huuphu.aprotrain.client_app.ViewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    Button btn_login ;
    EditText ed_username,ed_pass;
    TextView tv_register;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide()  ;

        btn_login = findViewById(R.id.btn_login);
        ed_username = findViewById(R.id.ed_username);
        ed_pass = findViewById(R.id.ed_pass);
        tv_register = findViewById(R.id.tv_register);
        btnBack = findViewById(R.id.imv_back_login);
        btn_login.setOnClickListener(view -> {
            hideKeyboardFrom(view.getContext(),view);
            LoginRequest request = new LoginRequest(
                    ed_username.getText().toString(),
                    ed_pass.getText().toString()
            );
            LoginViewModel.Login(request,view);
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tv_register.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), RegisterActivity.class);
            view.getContext().startActivity(intent);
        });

    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}