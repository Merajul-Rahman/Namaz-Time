package com.ShiponsWork.Namaz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;

import com.github.barteksc.pdfviewer.PDFView;

public class Allah_name extends AppCompatActivity {
    PDFView pdfView;
    Button bangla,english;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allah_name);
        bangla=findViewById(R.id.button);
        english=findViewById(R.id.button2);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bangla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.viewer_activity);
                pdfView=findViewById(R.id.pdfactivity);
                pdfView.fromAsset("allahnamebn.pdf").load();
            }
        });
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.viewer_activity);
                pdfView=findViewById(R.id.pdfactivity);
                pdfView.fromAsset("allahnameen.pdf").load();
            }
        });
    }
}
