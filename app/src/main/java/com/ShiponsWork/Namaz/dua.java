package com.ShiponsWork.Namaz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;

import com.github.barteksc.pdfviewer.PDFView;

public class dua extends AppCompatActivity {
    Toolbar toolbar;
    Button bisesdua,fazilotdua,ayatolkursi,duaiunus,mohamari,namazdua;
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dua);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bisesdua=findViewById(R.id.bisesdua);
        fazilotdua=findViewById(R.id.fazilotdua);
        ayatolkursi=findViewById(R.id.ayatulkorsi);
        duaiunus=findViewById(R.id.duaiunus);
        mohamari=findViewById(R.id.mohamari);
        namazdua=findViewById(R.id.namazdua);
        namazdua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.viewer_activity);
                pdfView=findViewById(R.id.pdfactivity);
                pdfView.fromAsset("namazerdua.pdf").load();
            }
        });
        bisesdua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.viewer_activity);
                pdfView=findViewById(R.id.pdfactivity);
                pdfView.fromAsset("bisesdua.pdf").load();
            }
        });
        fazilotdua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.viewer_activity);
                pdfView=findViewById(R.id.pdfactivity);
                pdfView.fromAsset("fazilotdua.pdf").load();
            }
        });
        ayatolkursi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.viewer_activity);
                pdfView=findViewById(R.id.pdfactivity);
                pdfView.fromAsset("ayatulkursi.pdf").load();
            }
        });
        duaiunus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.viewer_activity);
                pdfView=findViewById(R.id.pdfactivity);
                pdfView.fromAsset("duaiunus.pdf").load();
            }
        });
        mohamari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.viewer_activity);
                pdfView=findViewById(R.id.pdfactivity);
                pdfView.fromAsset("mohamari.pdf").load();
            }
        });

    }
}
