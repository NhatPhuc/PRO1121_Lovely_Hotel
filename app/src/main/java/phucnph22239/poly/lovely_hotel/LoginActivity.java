package phucnph22239.poly.lovely_hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Button btn_login;
    TextView tv_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_login);
        tv_register = findViewById(R.id.tv_register);

        tv_register.setOnClickListener(v -> {
            Intent intent1 = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent1);
        });


        btn_login.setOnClickListener(v -> {
            Intent intent2 = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent2);
        });
    }
}