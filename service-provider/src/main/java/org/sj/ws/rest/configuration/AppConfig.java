package org.sj.ws.rest.configuration;

import org.glassfish.jersey.server.ResourceConfig;
import org.sj.ws.rest.support.BloggingResourceV1;
import org.sj.ws.rest.support.BloggingResourceV2;

/**
 * Jersey resource configuration
 */
public class AppConfig extends ResourceConfig {

    public AppConfig() {
        register(BloggingResourceV1.class);
        register(BloggingResourceV2.class);
    }
}
