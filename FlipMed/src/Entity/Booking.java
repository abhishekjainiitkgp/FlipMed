package Entity;

import java.math.BigInteger;
import java.util.UUID;

public class Booking {
    String id;
    Doctor doctor;
    Patient patient;
    TimeSlot timeSlot;
    Boolean isCompleted;
    Boolean isCancelled;

    public Booking(Doctor doctor, Patient patient, TimeSlot timeSlot) {
        this.doctor = doctor;
        this.patient = patient;
        this.timeSlot = timeSlot;
        this.id= UUID.randomUUID().toString().replace("-", "").toString();
        this.isCompleted=false;
        this.isCancelled=false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", timeSlot=" + timeSlot +
                ", isCompleted=" + isCompleted +
                ", isCancelled=" + isCancelled +
                '}';
    }
}
