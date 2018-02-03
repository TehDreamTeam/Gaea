package nl.tehdreamteam.gaea.gateway.styx.stage;

import com.dexmatech.styx.core.http.HttpRequest;
import com.dexmatech.styx.core.http.utils.Uris;
import com.dexmatech.styx.core.pipeline.stages.StageResult;
import com.dexmatech.styx.core.pipeline.stages.request.RequestPipelineStage;
import nl.tehdreamteam.gaea.gateway.styx.StyxConstants;
import nl.tehdreamteam.gaea.gateway.util.RequestUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class ServiceRedirectingStage implements RequestPipelineStage {

    private static final Logger logger = LoggerFactory.getLogger(ServiceRedirectingStage.class);

    @Override
    public CompletableFuture<StageResult<HttpRequest>> apply(HttpRequest request) {
        logger.info("Requested service: '{}'.", request.getHeaders().get(StyxConstants.HEADER_SERVICE_TYPE));

        HttpRequest alteredRequest = transformWithNewHost(request);

        return RequestUtilities.completeSuccesfully(alteredRequest);
    }

    private HttpRequest transformWithNewHost(HttpRequest request) {
        String host = getNewHostAddress(request);

        return request.addHeader(StyxConstants.HEADER_REDIRECT_URI, host);
    }

    private String getNewHostAddress(HttpRequest request) {
        return Uris.changeHost(request.getRequestLine().getUri(), "nu.nl");
    }
}
