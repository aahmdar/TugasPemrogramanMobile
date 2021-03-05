package com.example.tugas2_pemrogramanmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Biodata extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata);
    }

    public void beranda(View view) {
        Intent a = new Intent(Biodata.this, MainActivity.class);
        startActivity(a);
        Toast.makeText(this, "Beranda", Toast.LENGTH_SHORT).show();
    }


    public void artikel(View view) {
        Intent a = new Intent(Biodata.this, Artikel.class);
        startActivity(a);
        Toast.makeText(this, "Artikel", Toast.LENGTH_SHORT).show();
    }
}