package com.ShiponsWork.Namaz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

public class jikir extends AppCompatActivity {
    TextView jikir1,jikir2,jikir3,jikir4;
    int jikirint1,jikirtint2,jikirint3,jikirint4;
    Button plus1,clear1,plus2,clear2,plus3,clear3,plus4,clear4;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jikir);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        jikirint1=0;
        jikirtint2=0;
        jikirint3=0;
        jikirint4=0;
        jikir1=findViewById(R.id.jikir1);
        plus1=findViewById(R.id.pllus);
        clear1=findViewById(R.id.clear);
        jikir2=findViewById(R.id.jikir2);
        plus2=findViewById(R.id.pllus2);
        clear2=findViewById(R.id.clear2);
        jikir3=findViewById(R.id.jikir3);
        plus3=findViewById(R.id.pllus3);
        clear3=findViewById(R.id.clear3);
        jikir4=findViewById(R.id.jikir4);
        plus4=findViewById(R.id.pllus4);
        clear4=findViewById(R.id.clear4);
        plus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jikirint1++;
                jikir1.setText(Integer.toString(jikirint1));
            }
        });
     clear1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             jikir1.setText("0");
             jikirint1=0;
         }
     });
        plus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jikirtint2++;
                jikir2.setText(Integer.toString(jikirtint2));
            }
        });
        clear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jikir2.setText("0");
                jikirtint2=0;
            }
        });
        plus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jikirint3++;
                jikir3.setText(Integer.toString(jikirint3));
            }
        });
        clear3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jikir3.setText("0");
                jikirint3=0;
            }
        });
        plus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jikirint4++;
                jikir4.setText(Integer.toString(jikirint4));
            }
        });
        clear4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jikir4.setText("0");
                jikirint4=0;
            }
        });
    }
}
