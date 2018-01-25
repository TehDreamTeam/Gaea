package nl.tehdreamteam.nurse.core;

import java.io.IOException;

public interface NurseServer {

    void start(int port) throws IOException;

    void stop() throws IOException;

    boolean isActive();

}
