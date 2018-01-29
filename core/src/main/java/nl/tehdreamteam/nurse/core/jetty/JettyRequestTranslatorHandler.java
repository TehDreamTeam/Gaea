package nl.tehdreamteam.nurse.core.jetty;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class JettyRequestTranslatorHandler extends AbstractHandler {

    private final JettyNurseServer server;

    public JettyRequestTranslatorHandler(JettyNurseServer server) {
        this.server = server;
    }

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) {
        server.fireRequestHandle(request, response);

        baseRequest.setHandled(true);
    }

}
