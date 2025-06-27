package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edtName, edtEmail, edtPassword, edtConfirmPassword;
    Button btnSignUp;
    TextView txtLoginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        txtLoginLink = findViewById(R.id.txtLoginLink);

        btnSignUp.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String pass = edtPassword.getText().toString().trim();
            String confirmPass = edtConfirmPassword.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!pass.equals(confirmPass)) {
                Toast.makeText(this, "Mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                return;
            }

            // TODO: Xử lý đăng ký (ghi vào database, Firebase, SQLite...)
            Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();


            startActivity(new Intent(MainActivity.this, MainActivity2.class));
            finish();
        });

        txtLoginLink.setOnClickListener(v -> {

            startActivity(new Intent(MainActivity.this, MainActivity2.class));
            finish();
        });
    }
}
