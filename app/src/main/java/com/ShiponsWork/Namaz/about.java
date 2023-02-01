package com.ShiponsWork.Namaz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

import com.github.barteksc.pdfviewer.PDFView;

public class about extends AppCompatActivity {
    Button button;
    Toolbar toolbar;
    String url;
    PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        button=findViewById(R.id.mail);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        url="https://www.gmail.com";
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage=Uri.parse(url);
                Intent intent=new Intent(Intent.ACTION_VIEW,webpage);
                if(intent.resolveActivity(getPackageManager())!=null)
                {
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_about, menu);
    return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.help:
                setContentView(R.layout.viewer_activity);
                pdfView=findViewById(R.id.pdfactivity);
                pdfView.fromAsset("help.pdf").load();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
