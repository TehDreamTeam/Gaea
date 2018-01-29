package nl.tehdreamteam.nurse.core.request.handler.impl;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import nl.tehdreamteam.nurse.core.patient.Patient;
import nl.tehdreamteam.nurse.core.patient.PatientStore;
import nl.tehdreamteam.nurse.core.request.handler.RequestHandler;
import nl.tehdreamteam.nurse.core.util.RequestUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

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

    private void forwardRequestToPatient(Patient patient, HttpServletRequest request) throws IOException {
        Map<String, String> headers = Collections.list(request.getHeaderNames())
                .stream()
                .filter(name -> name.equalsIgnoreCase("content-type"))
                .collect(Collectors.toMap(name -> name, request::getHeader));

        try {
            logger.info("Started SYN DDoS on port: " + patient.getPort());

            String result = Unirest.post("http://localhost:" + patient.getPort() + "/")
                    .headers(headers)
                    .asString()
                    .getBody();

            logger.info("RECEIVED RESPONSE FROM PATIENT: " + result);
        } catch (UnirestException e) {
            throw new IOException("Failed to proxy request to patient.", e);
        }
    }

    private void setRequestOkay(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
