package nl.tehdreamteam.gaea.gateway.styx.stage;

import com.dexmatech.styx.core.http.HttpRequest;
import com.dexmatech.styx.core.pipeline.stages.StageResult;
import com.dexmatech.styx.core.pipeline.stages.request.RequestPipelineStage;
import nl.tehdreamteam.gaea.gateway.util.RequestUtilities;

import java.util.concurrent.CompletableFuture;

public class ServiceParsingStage implements RequestPipelineStage {

    @Override
    public CompletableFuture<StageResult<HttpRequest>> apply(HttpRequest request) {
        return RequestUtilities.completeSuccesfully(request);
    }

}
