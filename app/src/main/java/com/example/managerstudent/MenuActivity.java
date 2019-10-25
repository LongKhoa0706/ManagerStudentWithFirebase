package com.example.managerstudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.managerstudent.Model.Lop;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuActivity extends AppCompatActivity {
    Button btnthemlop, btnxemds, btnquanlysv, btnxemquanly,btnthem,btnxoa;
    DatabaseReference noteRoot = FirebaseDatabase.getInstance().getReference();
     EditText edtThemMa,edtThemTen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        FirebaseApp.initializeApp(this);
        anhXa();
        btnthemlop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
                LayoutInflater layoutInflater = getLayoutInflater();
                View view1 = layoutInflater.inflate(R.layout.dialog_themlop, null, false);
                builder.setView(view1);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                edtThemMa   = alertDialog.findViewById(R.id.edtmalop);
                edtThemTen  = alertDialog.findViewById(R.id.edttenlop);
                 btnthem      = alertDialog.findViewById(R.id.btnluu);
                 btnxoa       = alertDialog.findViewById(R.id.btnxoa);

                btnthem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      dialogAdd();
                    }
                });
                btnxoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edtThemMa.setText("");
                        edtThemTen.setText("");
                    }
                });

            }
        });
        btnxemds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),XemDsActivity.class));
            }
        });
        btnquanlysv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),QuanLySinhVienActivity.class));
            }
        });
        btnxemquanly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),XemSvActivity.class));
            }
        });
    }
    private void anhXa() {
        btnthemlop = findViewById(R.id.btnthem);
        btnxemds = findViewById(R.id.btnxem);
        btnquanlysv = findViewById(R.id.btnqualy);
        btnxemquanly = findViewById(R.id.btnxemquanlysv);
    }
    private void dialogAdd(){
        String themma   = edtThemMa.getText().toString();
        String themTen  = edtThemTen.getText().toString();
        String key = noteRoot.child("Class").push().getKey();

        Lop aClass = new Lop();
        aClass.setMaLop(themma);
        aClass.setTenLop(themTen);
        aClass.setKey(key);

        noteRoot.child("Class").child(key).setValue(aClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MenuActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MenuActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
