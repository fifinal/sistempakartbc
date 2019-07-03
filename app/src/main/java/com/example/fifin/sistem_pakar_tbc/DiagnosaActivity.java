package com.example.fifin.sistem_pakar_tbc;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class DiagnosaActivity extends AppCompatActivity {

    public static final String EMAIL = "EMAIL";
    TextView tvPertanyaan,tvIdPertanyaan;
    RadioButton rbY,rbN;
    Button btnLanjut;
    String email;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference diagnosaFF=db.collection("diagnosa");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosa);
        tvPertanyaan=(TextView)findViewById(R.id.tv_pertanyaan);
        tvIdPertanyaan=(TextView)findViewById(R.id.tv_id_pertanyaan);
        rbY=(RadioButton) findViewById(R.id.rb_y);
        rbN=(RadioButton)findViewById(R.id.rb_n);
        btnLanjut=(Button) findViewById(R.id.btn_lanjut);
        email=getIntent().getStringExtra(this.EMAIL);

        getPertanyaanDariDb("G1");
        if (!rbY.isChecked()&&!rbN.isChecked()) btnLanjut.setClickable(false);
        else btnLanjut.setClickable(true);

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jawaban = null;
                if (rbY.isChecked()) jawaban="y";
                if(rbN.isChecked()) jawaban="n";
                btnLanjut.setBackgroundColor(Color.GRAY);
                btnLanjut.setText("Tunggu...");
                tvPertanyaan.setText("Loading..");
                btnLanjut.setClickable(false);

                getPertanyaan(jawaban);

                btnLanjut.setClickable(true);
                btnLanjut.setText("LANJUT");
                btnLanjut.setBackgroundColor(Color.rgb(0,87,75));
            }
        });
    }

    private void setTvPertanyaan(String pertanyaan, String idPertanyaan){
        Animation animation=AnimationUtils.loadAnimation(this,R.anim.animdiagnosa);
        tvPertanyaan.setText(pertanyaan);
        tvPertanyaan.startAnimation(animation);
        tvIdPertanyaan.setText(idPertanyaan);
    }

    private void getPertanyaan(final String jawaban){
        String id=tvIdPertanyaan.getText().toString().trim();
        diagnosaFF.document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(jawaban.equals("y")){
                    String id=documentSnapshot.getString("jawaban_ya").trim();
                    getPertanyaanDariDb(id);
                }else{
                    String id=documentSnapshot.getString("jawaban_tidak").trim();
                    getPertanyaanDariDb(id);
                }
            }
        });
    }

    private void getPertanyaanDariDb(String idBaru){

        if(idBaru.charAt(0)=='P'){
            Intent intent=new Intent(DiagnosaActivity.this,KesimpulanActivity.class);
            intent.putExtra(KesimpulanActivity.ID_PENYAKIT,idBaru);
            intent.putExtra(KesimpulanActivity.EMAIL,this.email);
            startActivity(intent);
        }else {
            diagnosaFF.document(idBaru).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                String kodeDiagnosa = documentSnapshot.getString("kode_diagnosa").trim();
                                String pertanyaan = documentSnapshot.getString("pertanyaan").trim();
                                setTvPertanyaan(pertanyaan, kodeDiagnosa);
                            } else {
                                tvPertanyaan.setText("Document tidak tersedia");
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            tvPertanyaan.setText("Gagal mendapakan data");
                        }
                    });
        }

    }
}
