package com.example.penilaian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    EditText editName,editSurname,editMarks,editTextId;

    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editText_name);
        editSurname =findViewById(R.id.editText_surname);
        editMarks = findViewById(R.id.editText_marks);
        editTextId =findViewById(R.id.editTextId);
    }

    public void add(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("nilai");

        String nama = editName.getText().toString();
        String surname = editSurname.getText().toString();
        String marks = editMarks.getText().toString();
        String id = editTextId.getText().toString();

        if (nama.equals("") || surname.equals("") || marks.equals("") || id.equals("")) {
            Toast.makeText(this, "Data ada yang kosong", Toast.LENGTH_SHORT).show();
        }else{
            Nilai nilai = new Nilai(Integer.valueOf(marks),nama, surname);
            myRef.child(id).setValue(nilai);

            Toast.makeText(this,"Data berhasil ditambahkan",Toast.LENGTH_SHORT).show();
        }

    }

    public void viewAll(View view) {
        data = "";
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("nilai");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                StringBuffer buffer = new StringBuffer();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Nilai nilaitest = postSnapshot.getValue(Nilai.class);
                    data += "Name: " + nilaitest.getNama() + "\n" +
                            "Surname: " +nilaitest.getSurname() + "\n"+
                            "Marks: " +nilaitest.getMarks() + "\n\n";
                }
                showMessage("Semua data", data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    //membuat alert dialog
    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}
