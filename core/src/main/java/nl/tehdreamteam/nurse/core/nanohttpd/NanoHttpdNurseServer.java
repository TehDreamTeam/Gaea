package nl.tehdreamteam.nurse.core.nanohttpd;

import fi.iki.elonen.NanoHTTPD;
import nl.tehdreamteam.nurse.core.NurseServer;
import nl.tehdreamteam.nurse.core.SynchronisedNurseServer;

import java.io.IOException;

public class NanoHttpdNurseServer extends SynchronisedNurseServer implements NurseServer {

    private EmbeddedNanoHttpdServer server;

    @Override
    protected void _start(int port) throws IOException {
        server = new EmbeddedNanoHttpdServer(port);
        server.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
    }

    @Override
    protected void _stop() {
        try {
            server.stop();
        } finally {
            server = null;
        }
    }

    @Override
    public boolean _isActive() {
        return server != null && server.isAlive();
    }
}
