package com.example.muzeumjegyfoglalas;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "appointment_table")
public class Appointment {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "day")
    private String day;

    @ColumnInfo(name = "time")
    private String time;

    @ColumnInfo(name = "exhibition")
    private String exhibition;

    @ColumnInfo(name = "ticketType")
    private String ticketType;

    public Appointment(String day, String time, String exhibition,String ticketType) {
        this.id = 0;
        this.day = day;
        this.time = time;
        this.exhibition = exhibition;
        this.ticketType = ticketType;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getExhibition() {
        return exhibition;
    }

    public void setExhibition(String exhibition) {
        this.exhibition = exhibition;
    }

    public String getAppointment(){
        return exhibition+"\n"+day+"\n"+time+"\n"+ticketType;
    }

    public String getTicketType() {return ticketType; }

    public void setTicketType(String ticketType) {this.ticketType = ticketType; }
}