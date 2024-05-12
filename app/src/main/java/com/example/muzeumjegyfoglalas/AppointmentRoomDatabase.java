package com.example.muzeumjegyfoglalas;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
@Database(entities = {Appointment.class}, version = 5, exportSchema = false)
public abstract class AppointmentRoomDatabase  extends RoomDatabase {
    public abstract AppointmentDAO appointmentDAO();

    private static AppointmentRoomDatabase instance;
    public static AppointmentRoomDatabase getInstance(Context context){
        if(instance==null){
            synchronized (AppointmentRoomDatabase.class){
                if(instance==null){
                    instance= Room.databaseBuilder(
                            context.getApplicationContext(), AppointmentRoomDatabase.class,
                            "appointment_database").fallbackToDestructiveMigration().addCallback(populationCallback).build();
                }
            }
        }
        return instance;
    }

    private static RoomDatabase.Callback populationCallback = new RoomDatabase.Callback(){
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            new InitDb(instance).execute();
        }
    };
    private static class InitDb extends AsyncTask<Void,Void,Void>{
        private AppointmentDAO dao;
        String[] appointments = {"1","2"};

        InitDb(AppointmentRoomDatabase db){
            this.dao = db.appointmentDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }



}