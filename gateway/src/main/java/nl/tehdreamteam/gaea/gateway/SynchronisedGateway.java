package nl.tehdreamteam.gaea.gateway;

import java.io.IOException;

public abstract class SynchronisedGateway implements Gateway {

    private final Object lock = new Object();

    @Override
    public final void bind(int port) throws IOException {
        close();

        synchronized (lock) {
            _bind(port);
        }
    }

    protected abstract void _bind(int port) throws IOException;

    @Override
    public final void close() {
        synchronized (lock) {
            _close();
        }
    }

    protected abstract void _close();

    @Override
    public final boolean isActive() {
        synchronized (lock) {
            return _isActive();
        }
    }

    protected abstract boolean _isActive();
}
