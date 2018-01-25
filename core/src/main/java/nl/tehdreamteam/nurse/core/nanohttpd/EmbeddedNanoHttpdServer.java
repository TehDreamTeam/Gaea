package nl.tehdreamteam.nurse.core.nanohttpd;

import fi.iki.elonen.NanoHTTPD;

public class EmbeddedNanoHttpdServer extends NanoHTTPD {

    public EmbeddedNanoHttpdServer(int port) {
        super(port);
    }

    @Override
    public Response serve(IHTTPSession session) {


        return newFixedLengthResponse("Henlo!");
    }

}
