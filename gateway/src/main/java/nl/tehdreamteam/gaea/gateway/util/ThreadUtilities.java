package nl.tehdreamteam.gaea.gateway.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public final class ThreadUtilities {

    private static final Logger logger = LoggerFactory.getLogger(ThreadUtilities.class);

    public static void shutdownAndAwaitTermination(ExecutorService executor) {
        executor.shutdown();

        try {
            if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                executor.shutdownNow();

                if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                    logger.warn("ExecutorService '{}' failed to terminate.", executor);
                }
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();

            Thread.currentThread().interrupt();
        }
    }

    private ThreadUtilities() {
        throw new UnsupportedOperationException("Should not be instantiated.");
    }

}
