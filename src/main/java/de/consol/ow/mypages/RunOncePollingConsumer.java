package de.consol.ow.mypages;

import org.apache.camel.Consumer;
import org.apache.camel.Endpoint;
import org.apache.camel.spi.PollingConsumerPollStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.concurrent.Executors;

@Named("RunOncePollingConsumer")
@ApplicationScoped
public class RunOncePollingConsumer implements PollingConsumerPollStrategy {

    private boolean ranOnce = false;

    private static final Logger LOG = LoggerFactory.getLogger(RunOncePollingConsumer.class);

    @Override
    public boolean begin(Consumer consumer, Endpoint endpoint) {
        try {
            if (ranOnce) {
                Executors.newSingleThreadExecutor().submit(()-> System.exit(0));
                return false;
            }
            else {
                return true;
            }
        }
        catch (Throwable e) {
            LOG.error("Exception stopping consumer", e);
            return false;
        }

    }

    @Override
    public void commit(Consumer consumer, Endpoint endpoint, int polledMessages) {
        LOG.info("Execution ended");
        ranOnce = true;
    }

    @Override
    public boolean rollback(Consumer consumer, Endpoint endpoint, int retryCounter, Exception cause) throws Exception {
        LOG.error("Rollback: Stopping consumer and camel context", cause);
        ranOnce = true;
        return false;
    }
}
