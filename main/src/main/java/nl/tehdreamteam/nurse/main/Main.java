package nl.tehdreamteam.nurse.main;

import nl.tehdreamteam.nurse.core.NurseServer;
import nl.tehdreamteam.nurse.core.jetty.JettyNurseServer;
import nl.tehdreamteam.nurse.core.patient.PatientStore;
import nl.tehdreamteam.nurse.core.patient.fixed.FixedPatientStore;
import nl.tehdreamteam.nurse.core.request.handler.impl.ClientRequestHandler;

import java.io.IOException;

public final class Main {

    public static void main(String[] args) throws IOException {
        PatientStore store = new FixedPatientStore();

        NurseServer server = new JettyNurseServer();
        server.addRequestHandler(new ClientRequestHandler(store));
        server.start(1200);
    }

}
