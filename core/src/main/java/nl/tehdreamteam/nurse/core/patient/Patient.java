package nl.tehdreamteam.nurse.core.patient;

import java.util.Objects;

public class Patient {

    private final String type;
    private final int port;

    public Patient(String type, int port) {
        this.type = Objects.requireNonNull(type);
        this.port = port;
    }

    public String getType() {
        return type;
    }

    public int getPort() {
        return port;
    }

}
