package Repository;

import Entity.Doctor;
import Entity.Speciality;
import Entity.TimeSlot;
import InputOutput.IOEnum;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorRespository {
    Map<String, Doctor> doctorsList;
    Map<Speciality, List<String> > doctorsListBySpeciality;

    private DoctorRespository(){
        doctorsList=new HashMap<>();
        doctorsListBySpeciality= new HashMap<>();

    }

    private static DoctorRespository doctorRespository=null;

    public static DoctorRespository getDoctorRespoInstance(){
        if(doctorRespository==null)
            doctorRespository=new DoctorRespository();
        return doctorRespository;
    }

    public void addDoctor(String name, Speciality speciality){
        Doctor doctor= new Doctor(name, speciality);
        System.out.println("1234");
        doctorsList.put(name, doctor);
        if(doctorsListBySpeciality.containsKey(speciality)) {
            doctorsListBySpeciality.get(speciality).add(name);
        }else{
            List<String> docNameList= new ArrayList<>();
            doctorsListBySpeciality.put(speciality, docNameList);
            doctorsListBySpeciality.get(speciality).add(name);
        }
    }

    public boolean addTimeSlot(String name, List<TimeSlot> slots){
        if(!doctorsList.containsKey(name))
            return false;
        for(TimeSlot slot: slots) {
            if (!doctorsList.get(name).getTimeSlotList().contains(slot))
                doctorsList.get(name).getTimeSlotList().add(slot);
        }
        return true;
    }

    public List<Doctor> getDoctorsBySpeciality(Speciality speciality){
        List<Doctor> doctorsBySpeciality= new ArrayList<>();
        if(doctorsListBySpeciality.containsKey(speciality)==false)
            return doctorsBySpeciality;
        for(String name:doctorsListBySpeciality.get(speciality)){
            doctorsBySpeciality.add(doctorsList.get(name));
        }
        return doctorsBySpeciality;
    }

    public Map<String, Doctor> getDoctorsList() {
        return doctorsList;
    }

    public void setDoctorsList(Map<String, Doctor> doctorsList) {
        this.doctorsList = doctorsList;
    }

    public Map<Speciality, List<String>> getDoctorsListBySpeciality() {
        return doctorsListBySpeciality;
    }

    public void setDoctorsListBySpeciality(Map<Speciality, List<String>> doctorsListBySpeciality) {
        this.doctorsListBySpeciality = doctorsListBySpeciality;
    }
}
