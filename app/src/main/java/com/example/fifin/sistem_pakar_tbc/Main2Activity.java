package com.example.fifin.sistem_pakar_tbc;

import android.content.Intent;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {

    TextView tvPertanyaan,tvIdPertanyaan;
    RadioButton rbY,rbN;
    Button btnLanjut;
    DataPertanyaan dataPertanyaan;
    HashMap<String,DataPertanyaan> diagnosa;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference gejalaFF=db.collection("gejala");
    private CollectionReference diagnosaFF=db.collection("diagnosa");
//    private DocumentReference noteRef = gejala.document("g1");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvPertanyaan=(TextView)findViewById(R.id.tv_pertanyaan);
        tvIdPertanyaan=(TextView)findViewById(R.id.tv_id_pertanyaan);
        rbY=(RadioButton) findViewById(R.id.rb_y);
        rbN=(RadioButton)findViewById(R.id.rb_n);
        btnLanjut=(Button) findViewById(R.id.btn_lanjut);
        diagnosa=new HashMap<>();

        dataPertanyaan=new DataPertanyaan("0","pertanyaan pertama" ,"1","2");
        diagnosa.put(String.valueOf(0),dataPertanyaan);

        dataPertanyaan=new DataPertanyaan("1","pertanyaan kedua" ,"2","2");
        diagnosa.put(String.valueOf(1),dataPertanyaan);

        dataPertanyaan=new DataPertanyaan("2","pertanyaan ketiga" ,"3","2");
        diagnosa.put(String.valueOf(2),dataPertanyaan);

        dataPertanyaan=new DataPertanyaan("3","pertanyaan keempat" ,"4","2");
        diagnosa.put(String.valueOf(3),dataPertanyaan);

        dataPertanyaan=new DataPertanyaan("4","pertanyaan kelima" ,"selesai","2");
        diagnosa.put(String.valueOf(4),dataPertanyaan);


//        DataPertanyaan data1=diagnosa.get("0");
        DocumentSnapshot idPertama=diagnosaFF.document("D1").get().getResult();
        String kodeDiagnosa= idPertama.getString("kode_diagnosa");
        String pertanyaan= idPertama.getString("pertanyaan");
        setTvPertanyaan(pertanyaan,kodeDiagnosa);

        if (!rbY.isChecked()&&!rbN.isChecked()) btnLanjut.setClickable(false);
        else btnLanjut.setClickable(true);

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jawaban = null;
                if (rbY.isChecked()) jawaban="y";
                if(rbN.isChecked()) jawaban="n";
                btnLanjut.setBackgroundColor(Color.GRAY);
                btnLanjut.setClickable(false);
                getPertanyaan(jawaban);
            }
        });
    }
//    https://developer.android.com/tools/building/multidex.html
    private void setTvPertanyaan(String pertanyaan, String idPertanyaan){
        btnLanjut.setClickable(true);
        btnLanjut.setBackgroundColor(Color.rgb(66,166,247));
        tvPertanyaan.setText(pertanyaan);
        tvIdPertanyaan.setText(idPertanyaan);
    }

    private void getPertanyaan(String jawaban){
        String id=tvIdPertanyaan.getText().toString();
        String idBaru=getIdBaru(id,jawaban);

        getPertanyaanDariDb(idBaru);

    }

    private String getIdBaru(String id, String jawaban){
        if(jawaban.equals("y")){
           return diagnosaFF.document(id).get().getResult().getString("jawaban_ya");
        }else{
            return diagnosaFF.document(id).get().getResult().getString("jawaban_tidak");
        }
    }

    private void getPertanyaanDariDb(final String idBaru){
        diagnosaFF.document(idBaru).get()
         .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
             @Override
             public void onSuccess(DocumentSnapshot documentSnapshot) {
                 if (documentSnapshot.exists()) {
                     String kodeDiagnosa = documentSnapshot.getString("kode_diagnosa");
                     String pertanyaan = documentSnapshot.getString("pertanyaan");
                     String selesai = documentSnapshot.getString("selesai");

                     if(selesai.equals("y")){
                         Intent intent=new Intent(Main2Activity.this,Main6Activity.class);
                         startActivity(intent);
                     }else{
                         setTvPertanyaan(pertanyaan,kodeDiagnosa);
                     }
                     //Map<String, Object> note = documentSnapshot.getData();
//                     textViewData.setText("Title: " + title + "\n" + "Description: " + description);
//                     Toast.makeText(Main2Activity.this, "Title: " + title + "\n" + "Description: " + description, Toast.LENGTH_SHORT).show();
                 } else {
                     Toast.makeText(Main2Activity.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                 }
             }
         })
         .addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                 Toast.makeText(Main2Activity.this, "Error! : "+e.toString(), Toast.LENGTH_SHORT).show();
//                 Log.d(TAG, e.toString());
             }
         });
       
    }
}
