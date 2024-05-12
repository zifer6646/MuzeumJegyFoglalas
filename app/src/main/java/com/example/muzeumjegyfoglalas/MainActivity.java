package com.example.muzeumjegyfoglalas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private  static final  String LOG_TAG = MainActivity.class.getName();
    private static final int SECRET_KEY = 99;

    EditText usernameET;
    EditText passwordET;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameET= findViewById(R.id.editTextUserName);
        passwordET= findViewById(R.id.editTextPassword);
        Log.i(LOG_TAG,"onCreate");
        mAuth = FirebaseAuth.getInstance();

    }

    public void login(View view) {


        String userName =  usernameET.getText().toString();
        String password =  passwordET.getText().toString();

        Log.i(LOG_TAG,"Bejelentkezett:"+userName+" jelszo:"+password);
        mAuth.signInWithEmailAndPassword(userName,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(LOG_TAG, "user created");
                    startBooking();
                }
                else{
                    Log.d(LOG_TAG,"not created");
                    Toast.makeText(MainActivity.this, "not created"+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void startBooking(){
    Intent intent = new Intent(this, Booking.class);
    intent.putExtra("SECRET_KEY",SECRET_KEY);

    startActivity(intent);
    overridePendingTransition(R.transition.t4, R.transition.t3);

   }

    public void register(View view) {
        Intent intent  = new Intent(this,RegisterActivity.class);
        intent.putExtra("SECRET_KEY",99);
        startActivity(intent);
    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG,"onStart");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG,"onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG,"onDestroy");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG_TAG,"onPause");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG,"onResume");
    }
}