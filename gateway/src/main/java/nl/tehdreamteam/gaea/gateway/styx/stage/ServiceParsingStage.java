package nl.tehdreamteam.gaea.gateway.styx.stage;

import com.dexmatech.styx.core.http.HttpRequest;
import com.dexmatech.styx.core.http.HttpResponse;
import com.dexmatech.styx.core.pipeline.stages.StageResult;
import com.dexmatech.styx.core.pipeline.stages.request.RequestPipelineStage;
import nl.tehdreamteam.gaea.gateway.styx.StyxConstants;
import nl.tehdreamteam.gaea.gateway.util.RequestUtilities;

import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceParsingStage implements RequestPipelineStage {

    private static final Pattern REGEX_FIRST_DIRECTORY = Pattern.compile("\\/([^\\/]+)\\/");

    @Override
    public CompletableFuture<StageResult<HttpRequest>> apply(HttpRequest request) {
        Matcher matcher = REGEX_FIRST_DIRECTORY.matcher(request.getRequestLine().getUri().getPath());
        if (!matcher.find()) {
            RequestUtilities.completeFailing(HttpResponse.NOT_FOUND, new IllegalArgumentException("Service not found in URI."));
        }

        String service = matcher.group(1);
        return RequestUtilities.completeSuccesfully(request.addHeader(StyxConstants.HEADER_SERVICE_TYPE, service));
    }

}
