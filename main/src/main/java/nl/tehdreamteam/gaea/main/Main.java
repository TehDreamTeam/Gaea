package nl.tehdreamteam.gaea.main;

import nl.tehdreamteam.gaea.gateway.Gateway;
import nl.tehdreamteam.gaea.gateway.styx.StyxGateway;

public final class Main {

    public static void main(String[] args) throws Exception {
        Gateway gateway = new StyxGateway();
        gateway.bind(1200);
    }

}
