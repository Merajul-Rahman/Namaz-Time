package com.ShiponsWork.Namaz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;

import com.github.barteksc.pdfviewer.PDFView;

public class menu extends AppCompatActivity {
Button button,button2,button3,button4,button6;
PDFView pdfView;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        button=findViewById(R.id.namaz);
        button2=findViewById(R.id.name);
        button3=findViewById(R.id.jikir);
        button4=findViewById(R.id.dua);
        button6=findViewById(R.id.hadis);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.viewer_activity);
                pdfView=findViewById(R.id.pdfactivity);
                pdfView.fromAsset("namaz.pdf").load();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(menu.this,Allah_name.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(menu.this,jikir.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(menu.this,dua.class);
                startActivity(intent);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.viewer_activity);
                pdfView=findViewById(R.id.pdfactivity);
                pdfView.fromAsset("hadis.pdf").load();
            }
        });

    }
}
