package com.example.tugas2_pemrogramanmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void artikel(View view) {
        Intent a = new Intent(MainActivity.this, Artikel.class);
        startActivity(a);
        Toast.makeText(this, "Artikel", Toast.LENGTH_SHORT).show();
    }

    public void biodata(View view) {
        Intent a = new Intent(MainActivity.this, Biodata.class);
        startActivity(a);
        Toast.makeText(this, "Biodata", Toast.LENGTH_SHORT).show();
    }
}