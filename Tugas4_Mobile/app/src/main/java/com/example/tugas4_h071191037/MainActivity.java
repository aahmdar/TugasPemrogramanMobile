package com.example.tugas4_h071191037;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText emailid,pass;
    Button login;
    TextView pageDaftar,lupaPass;
    CheckBox remember;

    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailid = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        login = findViewById(R.id.btnLogin);
        pageDaftar = findViewById(R.id.pagedaftar);
        lupaPass = findViewById(R.id.lupaPass);
        remember = findViewById(R.id.remember);

        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox = preferences.getString("remember","false");
        if (checkbox.equals(true)){
            Intent a = new Intent(MainActivity.this,HomePage.class);
            startActivity(a);
        } else if (checkbox.equals(false)){
            Toast.makeText(this, "Silahkan Login", Toast.LENGTH_SHORT).show();
        }

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null){
                    Toast.makeText(MainActivity.this, "Anda telah Login", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, HomePage.class);
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this, "Silahkan Login ", Toast.LENGTH_SHORT).show();
                }
            }
        };

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailid.getText().toString();
                String password = pass.getText().toString();
                if (email.isEmpty()){
                    emailid.setError("Masukkan Email");
                    emailid.requestFocus();
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailid.setError("Masukkan Email yang Benar");
                    emailid.requestFocus();
                    return;
                }
                else if (password.isEmpty()){
                    pass.setError("Masukkan password");
                    pass.requestFocus();
                }
                else if (!(email.isEmpty() && password.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                                Toast.makeText(MainActivity.this, "Email/Password Salah", Toast.LENGTH_SHORT).show();
                            else {
                                Intent i = new Intent(MainActivity.this, HomePage.class);
                                startActivity(i);
                            }
                        }
                    });
                }
                else
                    Toast.makeText(MainActivity.this, "Terjadi keselahan", Toast.LENGTH_SHORT).show();
            }
        });
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                    Toast.makeText(MainActivity.this, "Checked", Toast.LENGTH_SHORT).show();
                }
                else if (!compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(MainActivity.this, "Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        pageDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, Register.class);
                startActivity(a);
            }
        });

        lupaPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, LupaPassword.class);
                startActivity(a);
            }
        });
    }
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}