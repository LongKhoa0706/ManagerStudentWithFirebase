package com.example.managerstudent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.managerstudent.Adapter.SpinnerAdapter;
import com.example.managerstudent.Model.Lop;
import com.example.managerstudent.Model.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class QuanLySinhVienActivity extends AppCompatActivity {
    private Spinner spinner;
    private RecyclerView recyclerView;
    private EditText edtthemten,edtnhapthang;
    private Button btnthem;
    private int viTriSpinner = 0;
    private SpinnerAdapter spinnerAdapter;
    private ArrayList<Lop> arrSpinnerLop = new ArrayList<>();
    private DatabaseReference noteRoot = FirebaseDatabase.getInstance().getReference();
    private Calendar calendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_sinh_vien);
        anhXa();

        chonNgay();
        spinner();
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viTriSpinner = spinner.getSelectedItemPosition();
                String ten = edtthemten.getText().toString();
                String ngay = edtnhapthang.getText().toString();

                Student student = new Student();
                student.setTen(ten);
                student.setThang(ngay);
                student.setKey(arrSpinnerLop.get(viTriSpinner).getKey());

                noteRoot.child("Student").push().setValue(student).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(QuanLySinhVienActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(QuanLySinhVienActivity.this, "Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
    private void anhXa(){
        recyclerView    = findViewById(R.id.recycleviewStudent);
        spinner         = findViewById(R.id.spinnerlop);
        edtthemten      = findViewById(R.id.edtTen);
        edtnhapthang    = findViewById(R.id.edtnhapthang);
        btnthem         = findViewById(R.id.btnthemsv);
    }
    private void spinner(){
//
//        ValueEventListener eventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                DataSnapshot dataSpinner = dataSnapshot.child("Class");
//                for ( DataSnapshot valutSpinner : dataSpinner.getChildren()){
//                        Lop lop = (Lop) valutSpinner.getValue();
//                        arrSpinnerLop.add(lop);
//                        spinnerAdapter = new SpinnerAdapter(R.layout.activity_customspinner,getApplicationContext(),arrSpinnerLop);
//                        spinner.setAdapter(spinnerAdapter);
//                        spinnerAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        };
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Lop lop = dataSnapshot.getValue(Lop.class);
                arrSpinnerLop.add(lop);
                spinnerAdapter = new SpinnerAdapter(R.layout.activity_customspinner,getApplicationContext(),arrSpinnerLop);
                spinner.setAdapter(spinnerAdapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                viTriSpinner = arrSpinnerLop.get(i).getKey().length();
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
        viTriSpinner = spinner.getSelectedItemPosition();
        Toast.makeText(this, ""+viTriSpinner, Toast.LENGTH_SHORT).show();
        noteRoot.child("Class").addChildEventListener(childEventListener);
    }
    private void chonNgay(){
        edtnhapthang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
               int nam  = calendar.get(Calendar.YEAR);
               int tham = calendar.get(Calendar.MONTH);
               int ngay = calendar.get(Calendar.DATE);
                DatePickerDialog datePickerDialog = new DatePickerDialog(QuanLySinhVienActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendar.set(i,i1,i2);
                        edtnhapthang.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },nam,tham,ngay);
                datePickerDialog.show();
            }

        });

    }

}
