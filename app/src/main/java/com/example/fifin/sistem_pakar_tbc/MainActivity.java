package com.example.fifin.sistem_pakar_tbc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    public static final String EMAIL = "EMAIL";
    Intent inten;
    ImageButton diagnosa,info,tentang;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        diagnosa=(ImageButton) findViewById(R.id.btn_diagnosa);
        info=(ImageButton) findViewById(R.id.btn_info);
        tentang=(ImageButton) findViewById(R.id.btn_tentang);
        email=getIntent().getStringExtra(this.EMAIL);
        diagnosa.setOnClickListener(this);
        info.setOnClickListener(this);
        tentang.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_diagnosa:
                inten=new Intent(MainActivity.this,DiagnosaActivity.class);
                inten.putExtra(this.EMAIL,this.email);
                startActivity(inten);
                break;
            case R.id.btn_info:
                inten=new Intent(MainActivity.this,InfoActivity.class);
                startActivity(inten);
                break;
            case R.id.btn_tentang:
                inten=new Intent(MainActivity.this,TentangActivity.class);
                startActivity(inten);
                break;
        }

    }
}
