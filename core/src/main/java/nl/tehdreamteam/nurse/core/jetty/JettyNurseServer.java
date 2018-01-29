package nl.tehdreamteam.nurse.core.jetty;

import nl.tehdreamteam.nurse.core.NurseServer;
import nl.tehdreamteam.nurse.core.SynchronisedNurseServer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ErrorHandler;

import java.io.IOException;

public class JettyNurseServer extends SynchronisedNurseServer implements NurseServer {

    private final JettyRequestTranslatorHandler handler;
    private Server server;

    public JettyNurseServer() {
        handler = new JettyRequestTranslatorHandler(this);
    }

    @Override
    protected void _start(int port) throws IOException {
        server = new Server(port);
        server.setHandler(handler);
        enableErrorHandling(server);

        try {
            server.start();
        } catch (Exception e) {
            throw new IOException("Failed to start underlying Jetty server.", e);
        }
    }

    @Override
    protected void _stop() throws IOException {
        try {
            server.stop();
        } catch (Exception e) {
            throw new IOException("Failed to stop underlying Jetty server.", e);
        } finally {
            server = null;
        }
    }

    @Override
    public boolean _isActive() {
        return server != null && server.isStarted();
    }

    private static void enableErrorHandling(Server server) {
        ErrorHandler handler = new ErrorHandler();
        handler.setShowStacks(true);
        server.addBean(handler);
    }

}
