package com.example.muzeumjegyfoglalas;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.EventLogTags;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private  static final  String LOG_TAG = RegisterActivity.class.getName();
    private static final int SECRET_KEY = 77;
    private static final String CHANNEL_ID = "my_channel";
    private static final int NOTIFICATION_ID = 101;
    private static final int INTERNET_PERMISSION_REQUEST_CODE = 1;
    EditText userNameEditText;
    EditText emailEditText;
    EditText passwordEditText;
    EditText passwordAgainEditText;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        int secret_key = getIntent().getIntExtra("SECRET_KEY",0);

        if(secret_key!=99){
            finish();
        }

        userNameEditText= findViewById(R.id.userNameEditText);
        emailEditText= findViewById(R.id.userEmailEditText);
        passwordEditText= findViewById(R.id.passwordEditText);
        passwordAgainEditText= findViewById(R.id.passwordAgainEditText);


        mAuth = FirebaseAuth.getInstance();

        Log.i(LOG_TAG,"onCreate");

    }

    public void register(View view) {
        String userName =  userNameEditText.getText().toString();
        String email =  emailEditText.getText().toString();
        String password =  passwordEditText.getText().toString();
        String passwordAgain =  passwordAgainEditText.getText().toString();

        if(!password.equals(passwordAgain)){
            Log.e(LOG_TAG,"Nem jo a jelszo megerosites");
            return;
        }

        Log.i(LOG_TAG,"Regisztralt:"+userName+" jelszo:"+password);

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(LOG_TAG, "user created");
                    showRegistrationSuccessNotification();
                    startBooking();
                }
                else{
                    Log.d(LOG_TAG,"not created");
                    Toast.makeText(RegisterActivity.this, "not created"+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void showRegistrationSuccessNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Regisztráció sikeres")
                .setContentText("Sikeresen regisztráltál az alkalmazásba")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET}, INTERNET_PERMISSION_REQUEST_CODE);
        } else {
            performNetworkRequest();
        }

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void performNetworkRequest() {

    }


    public void cancel(View view) {
        finish();
    }


    private void startBooking(){
        Intent intent = new Intent(this, Booking.class);
        intent.putExtra("SECRET_KEY",SECRET_KEY);
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
