package com.example.tugas4_h071191037;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class LupaPassword extends AppCompatActivity {

    private EditText emailid;
    private Button btnResetPass;
    private TextView pageLogin;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);

        emailid = findViewById(R.id.email);
        btnResetPass = findViewById(R.id.btnReset);
        pageLogin = findViewById(R.id.pageLogin);

        auth = FirebaseAuth.getInstance();

        btnResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });

        pageLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(LupaPassword.this, MainActivity.class);
                startActivity(a);
            }
        });
    }

    private void resetPassword(){
        String email = emailid.getText().toString().trim();
        if (email.isEmpty()){
            emailid.setError("Masukkan Email");
            emailid.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailid.setError("Masukkan Email yang Benar");
            emailid.requestFocus();
            return;
        }
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LupaPassword.this, "Silahkan Periksa Email anda untuk mengganti Password",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(LupaPassword.this,"Email Belum Terdaftar", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}