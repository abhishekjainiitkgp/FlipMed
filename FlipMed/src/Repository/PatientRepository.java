package Repository;

import Entity.Patient;
import javafx.scene.shape.PathElement;

import java.util.HashMap;
import java.util.Map;

public class PatientRepository {
    Map<String, Patient> patientList;
    private static PatientRepository patientRepository=null;

    private PatientRepository(){
        patientList = new HashMap<>();
    }

    public static PatientRepository getPatientRepoInstance(){
        if(patientRepository==null)
            patientRepository= new PatientRepository();
        return patientRepository;
    }
    public void addPatient(String name){
        Patient patient= new Patient(name);
        patientList.put(name, patient);
    }

    public Map<String, Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(Map<String, Patient> patientList) {
        this.patientList = patientList;
    }
}
