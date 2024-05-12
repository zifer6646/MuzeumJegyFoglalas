package com.example.muzeumjegyfoglalas;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppointmentAdapter extends ArrayAdapter<Appointment> {

    private List<Appointment> appointments;

    public AppointmentAdapter(Context context, int resource, List<Appointment> appointments) {
        super(context, resource, appointments);
        this.appointments = appointments;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.appointment_list_item, parent, false);
        }

        Appointment appointment = appointments.get(position);

        TextView titleTextView = convertView.findViewById(R.id.appointment_title);
        titleTextView.setText(appointment.getAppointment());

        TextView idTextView = convertView.findViewById(R.id.appointment_id);
        idTextView.setText(String.valueOf(appointment.getId()));

        Button deleteButton = convertView.findViewById(R.id.delete_appointment_button);
        deleteButton.setTag(position);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Booking) getContext()).deleteAppointment(v);
            }
        });

        return convertView;
    }

    public LiveData<Appointment> getAppointmentById(int id) {
        return AppointmentRepo.getAppointmentById(id);
    }


}