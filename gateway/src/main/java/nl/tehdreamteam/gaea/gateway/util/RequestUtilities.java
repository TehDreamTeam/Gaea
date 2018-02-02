package nl.tehdreamteam.gaea.gateway.util;

import com.dexmatech.styx.core.http.HttpRequest;
import com.dexmatech.styx.core.http.HttpResponse;
import com.dexmatech.styx.core.pipeline.stages.StageResult;

import java.util.concurrent.CompletableFuture;

public final class RequestUtilities {

    public static CompletableFuture<StageResult<HttpRequest>> completeSuccesfully(HttpRequest request) {
        StageResult<HttpRequest> result = StageResult.stageSuccessWith(request);
        return CompletableFuture.completedFuture(result);
    }

    public static CompletableFuture<StageResult<HttpRequest>> completeFailing(HttpResponse response, Throwable cause) {
        StageResult<HttpRequest> result = StageResult.stageFailWith(response, cause);
        return CompletableFuture.completedFuture(result);
    }

    private RequestUtilities() {
        throw new UnsupportedOperationException("Should not be instantiated.");
    }

}
