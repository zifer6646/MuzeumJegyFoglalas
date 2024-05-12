package com.example.muzeumjegyfoglalas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class Booking extends AppCompatActivity {
    private  static final  String LOG_TAG = Booking.class.getName();
    private static final int SECRET_KEY = 88;


    private FirebaseUser user;
    private AppointmentViewModel vm;
    private AppointmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            Log.d(LOG_TAG,"user auth");
        }
        else{
            Log.d(LOG_TAG,"user not auth");
            finish();
        }

        vm = new ViewModelProvider(this).get(AppointmentViewModel.class);
        vm.getAppoints().observe(this, new Observer<List<Appointment>>() {
            @Override
            public void onChanged(List<Appointment> appointments) {
                ListView listView = findViewById(R.id.appointments_listview);
                adapter = new AppointmentAdapter(Booking.this, R.layout.appointment_list_item, appointments);
                listView.setAdapter(adapter);
            }
        });
    }


    public void newAppointment(View view) {
        Intent intent = new Intent(this, NewAppointmentActivity.class);
        intent.putExtra("SECRET_KEY",SECRET_KEY);
        startActivity(intent);


    }

    public void nAppointment(View view){
        Log.d(LOG_TAG,"pressed AF");
        Intent intent = new Intent(this, NewAppointmentActivity.class);
        intent.putExtra("SECRET_KEY",99);
        startActivity(intent);
        Log.d(LOG_TAG,"pressed AF2");
//   startActivity(new Intent(getApplicationContext(),Booking.class));
        overridePendingTransition(R.transition.tans_anim1, R.transition.tans_anim2);

    }
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.transition.t4, R.transition.t3);


    }

    public void deleteAppointment(View view) {
        int position = (int) view.getTag();
        Appointment appointment = adapter.getItem(position);
        vm.delete(appointment);
    }


}