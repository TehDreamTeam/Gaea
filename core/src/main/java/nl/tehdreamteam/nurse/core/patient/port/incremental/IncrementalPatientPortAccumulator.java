package nl.tehdreamteam.nurse.core.patient.port.incremental;

import nl.tehdreamteam.nurse.core.patient.port.PatientPortAccumulator;

import java.util.concurrent.atomic.AtomicInteger;

public class IncrementalPatientPortAccumulator implements PatientPortAccumulator {

    private static final int DEFAULT_MINIMUM_PORT = 1200;

    private final AtomicInteger nextPort;

    public IncrementalPatientPortAccumulator() {
        this(DEFAULT_MINIMUM_PORT);
    }

    public IncrementalPatientPortAccumulator(int minimumPort) {
        nextPort = new AtomicInteger(minimumPort);
    }

    @Override
    public int acquirePatientPort() {
        return nextPort.getAndIncrement();
    }
}
