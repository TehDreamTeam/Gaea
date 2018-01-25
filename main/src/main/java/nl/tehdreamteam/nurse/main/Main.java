package nl.tehdreamteam.nurse.main;

import nl.tehdreamteam.nurse.core.NurseServer;
import nl.tehdreamteam.nurse.core.jetty.JettyNurseServer;

import java.io.IOException;

public final class Main {

    public static void main(String[] args) throws IOException {
        NurseServer server = new JettyNurseServer();
        server.start(1201);
    }

}
