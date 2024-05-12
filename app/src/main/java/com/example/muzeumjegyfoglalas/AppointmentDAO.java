package com.example.muzeumjegyfoglalas;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AppointmentDAO {
    @Insert
    void insert(Appointment appointment);
    @Delete
    void delete(Appointment appointment);
    @Query("SELECT * FROM appointment_table ORDER BY day, time ASC")
    LiveData<List<Appointment>> getAppointments();

    @Query("SELECT * FROM appointment_table WHERE id = :id")
    LiveData<Appointment> getAppointmentById(int id);
}