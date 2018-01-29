package nl.tehdreamteam.nurse.core.request.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface RequestHandler {

    void handle(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
