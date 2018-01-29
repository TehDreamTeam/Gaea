package nl.tehdreamteam.nurse.core;

import nl.tehdreamteam.nurse.core.request.handler.RequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

public abstract class SynchronisedNurseServer implements NurseServer {

    private static final Logger logger = LoggerFactory.getLogger(SynchronisedNurseServer.class);

    private final Object lock = new Object();
    private final Collection<RequestHandler> handlers = new LinkedList<>();

    @Override
    public void addRequestHandler(RequestHandler handler) {
        handlers.add(handler);
    }

    @Override
    public void fireRequestHandle(HttpServletRequest request, HttpServletResponse response) {
        handlers.forEach(h -> {
            try {
                h.handle(request, response);
            } catch (IOException e) {
                logger.warn("Failed to handle request.", e);
            }
        });
    }

    @Override
    public final void start(int port) throws IOException {
        stop();

        synchronized (lock) {
            _start(port);
        }
    }

    protected abstract void _start(int port) throws IOException;

    @Override
    public final void stop() throws IOException {
        if (!isActive()) {
            return;
        }

        synchronized (lock) {
            _stop();
        }
    }

    protected abstract void _stop() throws IOException;

    @Override
    public boolean isActive() {
        synchronized (lock) {
            return _isActive();
        }
    }

    protected abstract boolean _isActive();

}
