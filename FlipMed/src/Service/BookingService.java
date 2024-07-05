package Service;

import Entity.Booking;
import Entity.Doctor;
import Entity.Patient;
import Entity.TimeSlot;
import Repository.BookingRepository;
import Repository.DoctorRespository;
import Repository.PatientRepository;
import javafx.util.Pair;

import javax.print.Doc;
import java.sql.Time;
import java.time.LocalTime;

public class BookingService {
    PatientRepository patientRepository;
    DoctorRespository doctorRespository;
    BookingRepository bookingRepository;

    public BookingService(){
        patientRepository=PatientRepository.getPatientRepoInstance();
        doctorRespository=DoctorRespository.getDoctorRespoInstance();
        bookingRepository=BookingRepository.getBookingRepoInstance();
    }

    public TimeSlot findDoctorAvailability(String docName, LocalTime startTime){
        Doctor doctor= doctorRespository.getDoctorsList().get(docName);
        for(TimeSlot slot : doctor.getTimeSlotList()){
            if(startTime==slot.getStartTime())
                return slot;
        }
        return null;
    }

    public Pair<Boolean, String> bookDoctorsAppointment(String patientName, String docName, LocalTime startTime){
        //chec if patient already has booking at same time
        Patient patient=patientRepository.getPatientList().get(patientName);
        for(String bookingId:patient.getBookingList()){
            TimeSlot slot=bookingRepository.getBookingAccepted().get(bookingId).getTimeSlot();
            if(slot.getStartTime()==startTime)
                return new Pair<>(false, null);
        }
        TimeSlot slot =findDoctorAvailability(docName, startTime);
        if(slot==null){
            return new Pair<>(false, null);
        }
        Booking booking=new Booking(doctorRespository.getDoctorsList().get(docName), patientRepository.getPatientList().get(patientName), slot);
        bookingRepository.bookAppointment(booking);
        Doctor doctor=booking.getDoctor();
        TimeSlot slot1=booking.getTimeSlot();
        doctor.getTimeSlotList().remove(slot);
        return new Pair<>(true, booking.getId());
    }

    public Boolean removeBooking(String bookingId){
        if(bookingRepository.getBookingAccepted().containsKey(bookingId)==false){
            return false;
        }
        Doctor doctor=bookingRepository.getBookingAccepted().get(bookingId).getDoctor();
        TimeSlot slot= bookingRepository.getBookingAccepted().get(bookingId).getTimeSlot();
        doctor.getTimeSlotList().add(slot);
        bookingRepository.cancelBooking(bookingId);
        return true;
    }





}
