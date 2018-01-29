package nl.tehdreamteam.nurse.core.patient.fixed;

import nl.tehdreamteam.nurse.core.patient.Patient;
import nl.tehdreamteam.nurse.core.patient.PatientStore;
import nl.tehdreamteam.nurse.core.patient.port.PatientPortAccumulator;
import nl.tehdreamteam.nurse.core.patient.port.incremental.IncrementalPatientPortAccumulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class FixedPatientStore implements PatientStore {

    private static final Logger logger = LoggerFactory.getLogger(FixedPatientStore.class);

    private final Map<String, Collection<Patient>> patients = new HashMap<>();
    private final PatientPortAccumulator portAccumulator;

    public FixedPatientStore() {
        this(new IncrementalPatientPortAccumulator());
    }

    public FixedPatientStore(PatientPortAccumulator portAccumulator) {
        this.portAccumulator = portAccumulator;
    }

    @Override
    public Patient getOrLoad(String type) {
        Collection<Patient> loadedPatients = getLoadedPatients(type);

        return loadedPatients.stream().findFirst().get();
    }

    private Collection<Patient> getLoadedPatients(String type) {
        return patients.computeIfAbsent(type, t -> {
            Collection<Patient> collection = new LinkedList<>();
            collection.add(loadPatient(t));

            return collection;
        });
    }

    private Patient loadPatient(String type) {
        logger.info("Loading new patient with type '{}'.", type);

        return new Patient(type, portAccumulator.acquirePatientPort());
    }
}
