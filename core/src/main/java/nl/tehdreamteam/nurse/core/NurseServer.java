package nl.tehdreamteam.nurse.core;

import nl.tehdreamteam.nurse.core.request.handler.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface NurseServer {

    void addRequestHandler(RequestHandler handler);

    void fireRequestHandle(HttpServletRequest request, HttpServletResponse response);

    void start(int port) throws IOException;

    void stop() throws IOException;

    boolean isActive();

}
