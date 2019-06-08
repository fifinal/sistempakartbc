package com.example.fifin.sistem_pakar_tbc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Main6Activity extends AppCompatActivity {

    TextView tvKesimpulanPenyakit, tvSolusi;
    ListView lvDaftarGejala;
    ArrayAdapter<String> adapter;
    String daftarGejala[]=new String[]{"gejala1","gejala2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        tvKesimpulanPenyakit=(TextView)findViewById(R.id.tv_kesimpulan_penyakit);
        tvSolusi=(TextView)findViewById(R.id.tv_solusi);
        lvDaftarGejala=(ListView) findViewById(R.id.lv_daftar_gejala);
        adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, daftarGejala);

        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        lvDaftarGejala.setAdapter(adapter);
    }
}
