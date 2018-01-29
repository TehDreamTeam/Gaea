package nl.tehdreamteam.nurse.core.request.handler.impl;

import nl.tehdreamteam.nurse.core.patient.Patient;
import nl.tehdreamteam.nurse.core.patient.PatientStore;
import nl.tehdreamteam.nurse.core.request.handler.RequestHandler;
import nl.tehdreamteam.nurse.core.util.RequestUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientRequestHandler implements RequestHandler {

    private static final Logger logger = LoggerFactory.getLogger(ClientRequestHandler.class);

    private final PatientStore store;

    public ClientRequestHandler(PatientStore store) {
        this.store = store;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.debug("Uri: '{}'.", request.getRequestURI());

        String patientType = RequestUtilities.extractPatientType(request);
        requireValidPatientType(patientType);

        Patient patient = store.getOrLoad(patientType);
        forwardRequestToPatient(patient, request);

        response.setContentType("text/plain");
        response.getWriter().println("ALLE MAAGDEN ZIJN KUT");
        setRequestOkay(response);
    }

    private void requireValidPatientType(String patientType) {
        if (!RequestUtilities.isValidPatientType(patientType)) {
            throw new IllegalArgumentException(String.format("Patient type '%s' is not valid.", patientType));
        }
    }

    private void forwardRequestToPatient(Patient patient, HttpServletRequest request) {

    }

    private void setRequestOkay(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
