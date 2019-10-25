package com.example.managerstudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnlogin, btnregister;
    SharedPreferences sharedPreferences;
    public  EditText editemail, editPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editemail = findViewById(R.id.edtemail);
        editPass = findViewById(R.id.edtpass);
        btnlogin = findViewById(R.id.dangnhap);
        btnregister = findViewById(R.id.dangky);
        sharedPreferences = getSharedPreferences("Login",MODE_PRIVATE);
        editemail.setText(sharedPreferences.getString("taikhoan",""));
        editPass.setText(sharedPreferences.getString("matkhau",""));

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = editemail.getText().toString().trim();
                String b = editPass.getText().toString();
//                if (editemail.length() == 0) {
//                    Toast.makeText(MainActivity.this, "Vui Lòng Nhập User!", Toast.LENGTH_SHORT).show();
//                    editemail.requestFocus();
//                    return;
//                } else if (editPass.length() == 0){
//                    Toast.makeText(MainActivity.this, "Vui Lòng Nhập Password!", Toast.LENGTH_SHORT).show();
//                    editPass.requestFocus();
//                    return;
//                }
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                overridePendingTransition(R.anim.animation_enter,R.anim.animation_exit);
            }
        });

    }
}
