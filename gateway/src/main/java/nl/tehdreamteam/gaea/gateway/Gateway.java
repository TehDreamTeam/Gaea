package nl.tehdreamteam.gaea.gateway;

import java.io.IOException;

public interface Gateway {

    void bind(int port) throws IOException;

    void close();

    boolean isActive();

}
