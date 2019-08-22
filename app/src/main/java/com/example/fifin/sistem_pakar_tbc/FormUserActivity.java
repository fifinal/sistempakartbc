package com.example.fifin.sistem_pakar_tbc;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FormUserActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference users=db.collection("users");
    EditText edtNama, edtEmail, edtAlamat, edtNoTelp, edtTempatLahir,edtTglLahir;
    RadioButton rbL,rbP;
    Button btnSimpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_user);

        edtNama=(EditText)findViewById(R.id.edt_nama);
        edtEmail=(EditText)findViewById(R.id.edt_email);
        edtAlamat=(EditText)findViewById(R.id.edt_alamat);
        edtNoTelp=(EditText)findViewById(R.id.edt_no_telp);
        edtTempatLahir=(EditText)findViewById(R.id.edt_tempat_lahir);
        edtTglLahir=(EditText)findViewById(R.id.edt_tgl_lahir);
        rbL=(RadioButton) findViewById(R.id.rb_l);
        rbP=(RadioButton) findViewById(R.id.rb_p);
        btnSimpan=(Button)findViewById(R.id.btn_simpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             dataUser();
            }
        });
    }
    private void dataUser(){
        String nama=edtNama.getText().toString().trim();
        String email=edtEmail.getText().toString().trim();
        String alamat=edtAlamat.getText().toString().trim();
        String noTelp=edtNoTelp.getText().toString().trim();
        String tempatLahir=edtTempatLahir.getText().toString().trim();
        String tglLahir=edtTglLahir.getText().toString().trim();
        String Gender="";
        if (nama.isEmpty())edtNama.setError("nama harus diisi");
        else if (email.isEmpty()) edtEmail.setError("email harus diisi");
        else if (alamat.isEmpty()) edtAlamat.setError("email harus diisi");
        else if (noTelp.isEmpty()) edtNoTelp.setError("email harus diisi");
        else if (tempatLahir.isEmpty()) edtTempatLahir.setError("email harus diisi");
        else if (tglLahir.isEmpty()) edtTglLahir.setError("email harus diisi");
        else {
            if (rbL.isChecked()) Gender="Laki-laki";
            else if (rbP.isChecked()) Gender="Perempuan";
            Map<String, Object> user = new HashMap<>();
            user.put("nama", nama);
            user.put("email", email);
            user.put("alamat", alamat);
            user.put("no_telp", noTelp);
            user.put("tempat_lahir", tempatLahir);
            user.put("tgl_lahir", tglLahir);
            user.put("jenis_kelamin", Gender);
            try{
                simpanDataUser(user);
            }catch (Exception e){
                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void simpanDataUser(Map<String,Object> user) {
        final String email=user.get("email").toString();
        users.document(email).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Intent intent=new Intent(FormUserActivity.this,DiagnosaActivity.class);
                intent.putExtra(DiagnosaActivity.EMAIL,email);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
