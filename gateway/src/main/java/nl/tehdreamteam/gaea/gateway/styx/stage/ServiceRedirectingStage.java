package nl.tehdreamteam.gaea.gateway.styx.stage;

import com.dexmatech.styx.core.http.HttpRequest;
import com.dexmatech.styx.core.http.utils.Uris;
import com.dexmatech.styx.core.pipeline.stages.StageResult;
import com.dexmatech.styx.core.pipeline.stages.request.RequestPipelineStage;
import com.dexmatech.styx.core.pipeline.stages.routing.DefaultRoutingStage;
import nl.tehdreamteam.gaea.gateway.util.RequestUtilities;

import java.util.concurrent.CompletableFuture;

public class ServiceRedirectingStage implements RequestPipelineStage {

    @Override
    public CompletableFuture<StageResult<HttpRequest>> apply(HttpRequest request) {
        HttpRequest alteredRequest = transformWithNewHost(request);

        return RequestUtilities.completeSuccesfully(alteredRequest);
    }

    private HttpRequest transformWithNewHost(HttpRequest request) {
        String host = getNewHostAddress(request);

        return request.addHeader(DefaultRoutingStage.DEFAULT_HEADER_USED_TO_ROUTE, host);
    }

    private String getNewHostAddress(HttpRequest request) {
        return Uris.changeHost(request.getRequestLine().getUri(), "nu.nl");
    }
}
