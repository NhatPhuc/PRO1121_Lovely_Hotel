package phucnph22239.poly.lovely_hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import phucnph22239.poly.lovely_hotel.DAO.QuanLyDAO;
import phucnph22239.poly.lovely_hotel.DTO.QuanLy;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText ed_guestName,ed_userName, ed_pass,ed_rePass;
    TextInputLayout til_guestName,til_userName, til_pass,til_rePass;
    Button btn_register;
    TextView tv_login;
    QuanLyDAO quanLyDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_register = findViewById(R.id.btn_register);
        tv_login = findViewById(R.id.tv_login);
        til_guestName = findViewById(R.id.til_name_register);
        til_userName = findViewById(R.id.til_username_register);
        til_pass = findViewById(R.id.til_pass_register);
        til_rePass = findViewById(R.id.til_repass_register);
        ed_guestName = findViewById(R.id.ed_name_register);
        ed_userName = findViewById(R.id.ed_username_register);
        ed_pass = findViewById(R.id.ed_pass_register);
        ed_rePass = findViewById(R.id.ed_repass_register);
        quanLyDAO = new QuanLyDAO(this);

        tv_login.setOnClickListener(v -> {
            Intent intent1 = new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent1);
        });

        btn_register.setOnClickListener(v -> {

            String name = ed_guestName.getText().toString();
            String user = ed_userName.getText().toString();
            String pass = ed_pass.getText().toString();
            String rePass = ed_rePass.getText().toString();

            if(name.equals("")||user.equals("")||pass.equals("")||rePass.equals("")) {
                if(name.equals("")) {
                    til_guestName.setError("Ch??a nh???p t??n");
                }else {
                    til_guestName.setError("");
                }
                if(user.equals("")) {
                    til_userName.setError("Ch??a nh???p t??n ????ng nh???p");
                }else{
                    if (quanLyDAO.checkUsername(user) == false) {
                        til_userName.setError("");
                    }else {
                        til_userName.setError("T??n ????ng nh???p ???? t???n t???i");
                    }
                }
                if(pass.equals("")) {
                    til_pass.setError("Ch??a nh???p m???t kh???u");
                }else{
                    til_pass.setError("");
                }
                if(rePass.equals("")) {
                    til_rePass.setError("Ch??a nh???p x??c nh???n m???t kh???u");
                }else {
                    til_rePass.setError("");
                }
            }else {
                til_guestName.setError("");
                til_userName.setError("");
                til_pass.setError("");
                til_rePass.setError("");
                if (pass.equals(rePass)) {

                    if (quanLyDAO.checkUsername(user) == false) {

                        QuanLy obj = new QuanLy();
                        obj.setName(name);
                        obj.setId(user);
                        obj.setPassword(pass);

                        if (quanLyDAO.insert(obj) > 0) {
                            Toast.makeText(RegisterActivity.this, "?????ng k?? th??nh c??ng", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent1);
                        } else {
                            Toast.makeText(RegisterActivity.this, "????ng k?? th???t b???i", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        til_userName.setError("T??n ????ng nh???p ???? t???n t???i");
                    }
                } else {
                    til_pass.setError("");
                    til_rePass.setError("M???t kh???u x??c nh???n sai");
                    if (quanLyDAO.checkUsername(user) == false) {

                        QuanLy obj = new QuanLy();
                        obj.setName(name);
                        obj.setId(user);
                        obj.setPassword(pass);

                        if (quanLyDAO.insert(obj) > 0) {
                            Toast.makeText(RegisterActivity.this, "?????ng k?? th??nh c??ng", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent1);
                        } else {
                            Toast.makeText(RegisterActivity.this, "????ng k?? th???t b???i", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        til_userName.setError("T??n ????ng nh???p ???? t???n t???i");
                    }
                }

            }

        });

    }
}