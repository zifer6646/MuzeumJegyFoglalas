package com.example.muzeumjegyfoglalas;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AppointmentViewModel extends AndroidViewModel {
    private AppointmentRepo repo;

    private LiveData<List<Appointment>> appoints;
    public AppointmentViewModel(Application application){
        super(application);
        this.repo = new AppointmentRepo(application);
        this.appoints = repo.getAppoints();
    }

    public LiveData<List<Appointment>> getAppoints(){
        return this.appoints;
    }

    public LiveData<Appointment> getAppointmentById(int id) {
        return AppointmentRepo.getAppointmentById(id);
    }


    public void insert(Appointment appointment){
        this.repo.insert(appointment);
    }
    public void delete(Appointment appointment) {
        repo.delete(appointment);
    }
}