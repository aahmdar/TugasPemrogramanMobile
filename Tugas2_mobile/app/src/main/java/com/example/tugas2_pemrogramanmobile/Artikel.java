package com.example.tugas2_pemrogramanmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Artikel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);
    }

    public void biodata(View view) {
        Intent a = new Intent(Artikel.this, Biodata.class);
        startActivity(a);
        Toast.makeText(this, "Biodata", Toast.LENGTH_SHORT).show();
    }

    public void beranda(View view) {
        Intent a = new Intent(Artikel.this, MainActivity.class);
        startActivity(a);
        Toast.makeText(this, "Beranda", Toast.LENGTH_SHORT).show();
    }
}