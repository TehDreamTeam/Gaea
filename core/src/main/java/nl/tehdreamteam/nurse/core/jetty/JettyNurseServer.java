package nl.tehdreamteam.nurse.core.jetty;

import nl.tehdreamteam.nurse.core.NurseServer;
import org.eclipse.jetty.server.Server;

import java.io.IOException;

public class JettyNurseServer implements NurseServer {

    private final Object lock = new Object();

    private Server server;

    @Override
    public void start(int port) throws IOException {
        stop();

        synchronized (lock) {
            server = new Server(port);
            server.setHandler(new JettyNurseHandler());

            try {
                server.start();
            } catch (Exception e) {
                throw new IOException("Failed to start underlying Jetty server.", e);
            }
        }
    }

    @Override
    public void stop() throws IOException {
        synchronized (lock) {
            if (!isActive()) {
                return;
            }

            try {
                server.stop();
            } catch (Exception e) {
                throw new IOException("Failed to stop underlying Jetty server.", e);
            } finally {
                server = null;
            }
        }
    }

    @Override
    public boolean isActive() {
        return server != null && server.isRunning();
    }

}
