package nl.tehdreamteam.nurse.core;

import java.io.IOException;

public abstract class SynchronisedNurseServer implements NurseServer {

    private final Object lock = new Object();


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
