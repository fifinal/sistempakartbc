package com.example.fifin.sistem_pakar_tbc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KesimpulanActivity extends AppCompatActivity {

    public static final String ID_PENYAKIT = "ID_PENYAKIT";
    public static final String EMAIL = "EMAIL";
    TextView tvKesimpulanPenyakit, tvSolusi;
    ListView lvDaftarGejala;
    ArrayAdapter<String> adapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference penyakit=db.collection("penyakit");
    private CollectionReference hasilDiagnosa=db.collection("hasil_diagnosa");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kesimpulan);
        tvKesimpulanPenyakit=(TextView)findViewById(R.id.tv_kesimpulan_penyakit);

        String idPenyakit=getIntent().getStringExtra(ID_PENYAKIT);
        String email=getIntent().getStringExtra(EMAIL);

        Map<String, Object> hasil = new HashMap<>();
        hasil.put("email", email);
        hasil.put("tanggal", new Timestamp(new Date()));
        hasil.put("id_penyakit",idPenyakit);
        hasilDiagnosa.document(email).set(hasil).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });

        penyakit.document(idPenyakit).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String namaPenyakit=documentSnapshot.getString("nama_penyakit");
                tvKesimpulanPenyakit.setText(namaPenyakit);
            }
        });
    }
}
