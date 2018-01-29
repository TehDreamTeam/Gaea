package nl.tehdreamteam.nurse.core.util;

import nl.tehdreamteam.nurse.core.patient.Patient;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public final class RequestUtilities {

    public static void forward(HttpServletRequest request, Patient patient) {

    }

    public static String extractPatientType(HttpServletRequest request) {
        return request.getHeader("destination-patient-type");
    }

    public static boolean isValidPatientType(String type) {
        return Objects.nonNull(type) && !type.trim().isEmpty();
    }

    private RequestUtilities() {
        throw new UnsupportedOperationException("Should not be instantiated.");
    }

}
