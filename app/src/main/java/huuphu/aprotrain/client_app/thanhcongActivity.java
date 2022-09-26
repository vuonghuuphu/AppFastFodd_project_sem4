package huuphu.aprotrain.client_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import java.util.Objects;

import huuphu.aprotrain.client_app.View.Screen.LoginActivity;
import huuphu.aprotrain.client_app.View.Screen.ScreenStartActivity;
import huuphu.aprotrain.client_app.data.Constants;

public class thanhcongActivity extends AppCompatActivity {
    private final Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhcong);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        mHandler.postDelayed(() -> {
            Intent intent;
            intent = new Intent(thanhcongActivity.this, MainActivity.class);
            intent.putExtra("navigation", 3 );
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }, 3000);

    }
}