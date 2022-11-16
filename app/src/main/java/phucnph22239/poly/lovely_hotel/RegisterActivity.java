package phucnph22239.poly.lovely_hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    Button btn_register;
    TextView tv_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_register = findViewById(R.id.btn_register);
        tv_login = findViewById(R.id.tv_login);

        tv_login.setOnClickListener(v -> {
            Intent intent1 = new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent1);
        });

        btn_register.setOnClickListener(v -> {
            Intent intent1 = new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent1);
        });

    }
}