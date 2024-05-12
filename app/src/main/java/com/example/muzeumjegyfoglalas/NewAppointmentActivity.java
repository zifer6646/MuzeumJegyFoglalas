package com.example.muzeumjegyfoglalas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewAppointmentActivity extends AppCompatActivity {
    private AppointmentViewModel appointmentViewModel;
    private static final String LOG_TAG = NewAppointmentActivity.class.getName();

    private FirebaseUser user;

    Spinner dateSpinner;
    Spinner timeSpinner;
    Spinner exhibSpinner;
    Spinner ticketTypeSpinner;
    Calendar calendar = Calendar.getInstance();
    Date currentDate = calendar.getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "pressed AF55");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appointment);
        int secret_key = getIntent().getIntExtra("SECRET_KEY", 0);
        appointmentViewModel = new ViewModelProvider(this).get(AppointmentViewModel.class);

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Log.d(LOG_TAG, "user auth");
        } else {
            Log.d(LOG_TAG, "user not auth");
            finish();
        }
        // Create a list of dates
        List<Date> dateList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            calendar.add(Calendar.DATE, 1);
            dateList.add(calendar.getTime());
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM d", new Locale("hu", "HU"));
        List<String> dateStringList = new ArrayList<>();
        for (Date date : dateList) {
            dateStringList.add(sdf.format(date));
        }

        // Create the spinner and set the adapter
        dateSpinner = findViewById(R.id.dateSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dateStringList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateSpinner.setAdapter(adapter);

        timeSpinner = findViewById(R.id.appointment_time_spinner);

        exhibSpinner = findViewById(R.id.appointment_exhibition_spinner);

        ticketTypeSpinner = findViewById(R.id.appointment_ticketType_spinner);


        Button saveButton = findViewById(R.id.save_appointment_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveAppointmentButtonClick();
            }
        });
    }


    public void back(View view){
        finish();
    }

    public void onSaveAppointmentButtonClick() {
        // Get the values from the input fields
        String date = dateSpinner.getSelectedItem().toString();
        String time = timeSpinner.getSelectedItem().toString();
        String exhibition = exhibSpinner.getSelectedItem().toString();
        String ticketType = ticketTypeSpinner.getSelectedItem().toString();

        // Create a new appointment object
        Appointment newAppointment = new Appointment(date, time, exhibition, ticketType);

        // Insert the new appointment into the database
        appointmentViewModel.insert(newAppointment);


        finish();
    }
}