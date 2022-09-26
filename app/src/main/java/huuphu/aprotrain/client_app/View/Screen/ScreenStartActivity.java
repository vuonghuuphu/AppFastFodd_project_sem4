package huuphu.aprotrain.client_app.View.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import java.util.Objects;

import huuphu.aprotrain.client_app.MainActivity;
import huuphu.aprotrain.client_app.Model.EnumApp;
import huuphu.aprotrain.client_app.R;
import huuphu.aprotrain.client_app.data.Constants;
import huuphu.aprotrain.client_app.data.SharedPreferencesHelper;
import vn.zalopay.sdk.ZaloPaySDK;

public class ScreenStartActivity extends AppCompatActivity {
    private final Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_start);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Constants.token = SharedPreferencesHelper.ReadUser(EnumApp.KeyToken,this);
        System.out.println("Constants.token++ " + Constants.token);
        mHandler.postDelayed(() -> {
            Intent intent;
//            if (Constants.token == null){
//                intent = new Intent(ScreenStartActivity.this, LoginActivity.class);
//            }else {
                intent = new Intent(ScreenStartActivity.this, MainActivity.class);
//            }
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }, 3000);
    }
}