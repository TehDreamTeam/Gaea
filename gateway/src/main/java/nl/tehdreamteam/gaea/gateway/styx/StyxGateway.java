package nl.tehdreamteam.gaea.gateway.styx;

import com.dexmatech.styx.core.ApiPipeline;
import com.dexmatech.styx.core.pipeline.HttpRequestReplyPipeline;
import com.dexmatech.styx.modules.grizzly.ApiGateway;
import nl.tehdreamteam.gaea.gateway.SynchronisedGateway;
import nl.tehdreamteam.gaea.gateway.styx.stage.ServiceParsingStage;
import nl.tehdreamteam.gaea.gateway.styx.stage.ServiceRedirectingStage;
import nl.tehdreamteam.gaea.gateway.util.ThreadUtilities;

public class StyxGateway extends SynchronisedGateway {

    private final ApiPipeline pipeline;
    private ApiGateway gateway;

    public StyxGateway() {
        pipeline = buildStyxPipeline();
    }

    private ApiPipeline buildStyxPipeline() {
        HttpRequestReplyPipeline pipeline = HttpRequestReplyPipeline.pipeline()
                .applyingPreRoutingStage("service-parsing", new ServiceParsingStage())
                .applyingPreRoutingStage("service-redirecting", new ServiceRedirectingStage())
                .applyingDefaultRoutingStage()
                .build();

        return ApiPipeline.singlePipeline().using(pipeline).build();
    }

    @Override
    protected void _bind(int port) {
        gateway = ApiGateway.runningOverGrizzly()
                .withPipeline(pipeline)
                .withDefaultServerRunningOnPort(port)
                .build();
        gateway.start();
    }

    @Override
    protected void _close() {
        if (gateway != null) {
            ThreadUtilities.shutdownAndAwaitTermination(gateway.getExecutorService());

            gateway.shutdown();
            gateway = null;
        }
    }

    @Override
    protected boolean _isActive() {
        return gateway != null && gateway.getHttpServer().isStarted();
    }

}
