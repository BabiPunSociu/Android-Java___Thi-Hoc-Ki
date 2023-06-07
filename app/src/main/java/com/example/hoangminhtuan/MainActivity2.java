package com.example.hoangminhtuan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //Lấy dữ liệu khỏi Bundle
        EditText sx;
        Bundle b=getIntent().getExtras();
        if(b!=null){
            Taxi_hoangminhtuan user=new Taxi_hoangminhtuan();
            String soXe=b.getString("soXe");
            sx=findViewById(R.id.edSoXe);
            sx.setText(soXe.toString());
        }
        Button thoat=findViewById(R.id.button2);
        Button them=findViewById(R.id.button3);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}