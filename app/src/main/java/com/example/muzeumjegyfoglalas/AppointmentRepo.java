package com.example.muzeumjegyfoglalas;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.nio.channels.AsynchronousChannelGroup;
import java.util.List;

public class AppointmentRepo {
    private static AppointmentDAO dao;
    private LiveData<List<Appointment>> appoints;

    AppointmentRepo(Application application){
        AppointmentRoomDatabase db= AppointmentRoomDatabase.getInstance(application);
        dao=db.appointmentDAO();
        appoints=dao.getAppointments();
    }

    public LiveData<List<Appointment>> getAppoints() {
        appoints = dao.getAppointments();
        return appoints;
    }


    public void insert(Appointment appointment){
        new Insert(this.dao).execute(appointment);
    }

    private  static class Insert extends AsyncTask<Appointment, Void, Void> {
        private AppointmentDAO aDAO;
        Insert(AppointmentDAO dao){
            this.aDAO = dao;
        }

        @Override
        protected Void doInBackground(Appointment... appointments) {
            aDAO.insert(appointments[0]);
            return null;
        }
    }

    public static LiveData<Appointment> getAppointmentById(int id) {
        return dao.getAppointmentById(id);
    }

    public void delete(Appointment appointment) {
        new DeleteTask(this.dao).execute(appointment);
    }

    private static class DeleteTask extends AsyncTask<Appointment, Void, Void> {
        private AppointmentDAO aDAO;

        DeleteTask(AppointmentDAO dao){
            this.aDAO = dao;
        }

        @Override
        protected Void doInBackground(Appointment... appointments) {
            aDAO.delete(appointments[0]);
            return null;
        }
    }
}