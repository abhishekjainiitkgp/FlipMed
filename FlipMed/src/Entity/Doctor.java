package Entity;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Doctor {
    String name;
    Speciality speciality;
    List<TimeSlot> timeSlotList;
    List<String> bookingList;

    public Doctor(String name, Speciality speciality) {
        this.name = name;
        this.speciality = speciality;
        this.bookingList=new ArrayList<>();
        this.timeSlotList=new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public List<TimeSlot> getTimeSlotList() {
        return timeSlotList;
    }

    public void setTimeSlotList(List<TimeSlot> timeSlotList) {
        this.timeSlotList = timeSlotList;
    }

    public List<String> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<String> bookingList) {
        this.bookingList = bookingList;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + name + '\'' +
                ", speciality=" + speciality +
                ", timeSlotList=" + timeSlotList +
                ", bookingList=" + bookingList +
                '}';
    }
}
