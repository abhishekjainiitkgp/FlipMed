import Entity.Doctor;
import Entity.Patient;
import Entity.Speciality;
import Entity.TimeSlot;
import InputOutput.IOEnum;
import Repository.BookingRepository;
import Repository.DoctorRespository;
import Repository.PatientRepository;
import Service.BookingService;
import javafx.util.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOService {
    PatientRepository patientRepository;
    DoctorRespository doctorRespository;
    BookingRepository bookingRepository;
    BookingService bookingService;

    public IOService(){
        patientRepository= PatientRepository.getPatientRepoInstance();
        doctorRespository= DoctorRespository.getDoctorRespoInstance();
        bookingRepository= BookingRepository.getBookingRepoInstance();
        bookingService= new BookingService();
    }

    public void takeInput(){
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Enter the command - {registerDoc, markDocAvailable, showAvailabilityBySpeciality, registerPatient, bookAppointment, cancelBooking, end}");
            IOEnum inputCommand = null;
            try {
                inputCommand = IOEnum.valueOf(sc.next());
            } catch (Exception e) {
                System.out.println("incorrect command, please try again");
                continue;
            }
            switch(inputCommand){
                case registerDoc: {
                    System.out.println("Please enter doctor name");
                    String docName = sc.next();
                    Speciality speciality;
                    System.out.println("Please enter doctor speciality- {Cardiologist, Dermatologist, Orthopedic, GeneralPhysician}");
                    try {
                        speciality = Speciality.valueOf(sc.next());
                    } catch (Exception e) {
                        System.out.println("you enter un-registered Speciality, redirecting to start");
                        break;
                    }
                    doctorRespository.addDoctor(docName, speciality);
                    System.out.println("Dr. " + docName + " registered successfully");
                    break;
                }
                case markDocAvailable:{
                    System.out.println("Please enter doctor name for whom you want to mark slots available");
                    String docName=sc.next();
                    System.out.println("Enter the number of slots you want to enter");
                    int nSlots=sc.nextInt();
                    List<TimeSlot> timeSlots= new ArrayList<>();
                    for(int i=0;i<nSlots;i++){
                        System.out.println("Please enter slot "+ i + " start time in HH:MM format (10:00, only one hour slot)" );
                        LocalTime startTime= LocalTime.parse(sc.next());
                        System.out.println("Please enter slot "+ i + " end time in HH:MM format (10:00, only one hour slot)" );
                        LocalTime endTime=LocalTime.parse(sc.next());
                        timeSlots.add(new TimeSlot(startTime, endTime));
                    }
                    if(doctorRespository.addTimeSlot(docName, timeSlots)){
                        System.out.println("Time slots added for Dr. "+ docName);
                    }
                    else
                        System.out.println("Dr. "+ docName + " not registered. Please register before marking available slots");
                    break;
                }
                case registerPatient:{
                    System.out.println("Please enter patient name");
                    String patientName = sc.next();
                    patientRepository.addPatient(patientName);
                    break;
                }
                case showAvailabilityBySpeciality:{
                    System.out.println("Please enter the speciality - {Cardiologist, Dermatologist, Orthopedic, GeneralPhysician}");
                    Speciality speciality;
                    try{
                        speciality=Speciality.valueOf(sc.next());
                    }catch(Exception e){
                        System.out.println("You entered wrong speciality. Please start again and enter correct speciality");
                        break;
                    }
                    List<Doctor> doctorsList= doctorRespository.getDoctorsBySpeciality(speciality);
                    if(doctorsList.isEmpty()){
                        System.out.println("No slot available");
                        break;
                    }
                    for(Doctor doctor :doctorsList){
                        for(TimeSlot slot:doctor.getTimeSlotList()){
                            System.out.println("Dr. "+ doctor.getName()+ ": " +": ["+ slot.getStartTime()+ ", "+ slot.getEndTime()+ "]" );
                        }
                    }
                    break;
                }
                case bookAppointment:{
                    System.out.println("Enter patient name for appointment");
                    String patientName=sc.next();
                    if(patientRepository.getPatientList().containsKey(patientName)==false){
                        System.out.println("your entered patient is not registered, start booking again with correct patient name");
                        break;
                    }
                    System.out.println("Enter doctor name for appointment");
                    String docName=sc.next();
                    if(doctorRespository.getDoctorsList().containsKey(docName)==false){
                        System.out.println("your entered doctor is not registered, start booking again with correct patient name");
                        break;
                    }
                    System.out.println("Enter the starting time of appointment");
                    LocalTime startTime= LocalTime.parse(sc.next());
                    Pair<Boolean, String> bookingStatus=bookingService.bookDoctorsAppointment(patientName, docName, startTime);
                    if(bookingStatus.getKey()==false){
                        System.out.println("provided slot is not available, either doctor not available or you have different appointment");
                    }
                    System.out.println("Appointment confirmed with booking id: "+ bookingStatus.getValue());
                    break;
                }
                case cancelBooking:{
                    System.out.println("Please enter the booking id for booking you want to cancel");
                    String bookingId= sc.next();
                    boolean cancelStatus=bookingService.removeBooking(bookingId);
                    if(cancelStatus==false)
                        System.out.println("Booking id you wrong or booking already completed");
                    System.out.println("Booking cancellation successful");
                    break;

                }
                case end:{
                    return;
                }


            }
        }

    }
}
