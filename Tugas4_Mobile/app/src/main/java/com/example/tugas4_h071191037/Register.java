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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText emailid,pass;
    Button daftar;
    TextView pageLogin;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailid = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        daftar = findViewById(R.id.btnDaftar);
        pageLogin = findViewById(R.id.pageLogin);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailid.getText().toString();
                String pwd = pass.getText().toString();

                if (email.isEmpty()){
                    emailid.setError("Masukkan Email");
                    emailid.requestFocus();
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailid.setError("Masukkan Email yang Benar");
                    emailid.requestFocus();
                    return;
                }
                else if (pwd.isEmpty()){
                    pass.setError("Masukkan Password");
                    pass.requestFocus();
                }
                if (pwd.length() < 6){
                    pass.setError("Minimal 6 Karakter");
                    pass.requestFocus();
                    return;
                }
                else if (!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Register.this, "Pendaftaran Gagal", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(Register.this, MainActivity.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(Register.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            }
        });
        pageLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Register.this, MainActivity.class);
                startActivity(a);
            }
        });
    }
}