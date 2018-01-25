package nl.tehdreamteam.nurse.main;

import nl.tehdreamteam.nurse.core.NurseServer;
import nl.tehdreamteam.nurse.core.nanohttpd.NanoHttpdNurseServer;

import java.io.IOException;

public final class Main {

    public static void main(String[] args) throws IOException {
        NurseServer server = new NanoHttpdNurseServer();
        server.start(1201);
    }

}
