package com.example.fifin.sistem_pakar_tbc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Intent inten;
    ImageButton btn1,btn2,btn3,btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=(ImageButton) findViewById(R.id.btn1);
        btn2=(ImageButton) findViewById(R.id.btn2);
        btn3=(ImageButton) findViewById(R.id.btn3);
        btn4=(ImageButton) findViewById(R.id.btn4);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                inten=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(inten);
                break;
            case R.id.btn2:
                inten=new Intent(MainActivity.this,Main3Activity.class);
                startActivity(inten);
                break;
            case R.id.btn3:
                inten=new Intent(MainActivity.this,Main4Activity.class);
                startActivity(inten);
                break;
            case R.id.btn4:
                inten=new Intent(MainActivity.this,Main5Activity.class);
                startActivity(inten);
                break;
        }

    }
}
