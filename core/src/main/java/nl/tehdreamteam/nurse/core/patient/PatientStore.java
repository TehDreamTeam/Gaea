package nl.tehdreamteam.nurse.core.patient;

public interface PatientStore {

    Patient getOrLoad(String type);

}
