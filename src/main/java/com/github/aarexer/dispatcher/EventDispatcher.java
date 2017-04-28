package com.github.aarexer.dispatcher;

import org.apache.ignite.Ignite;
import org.apache.ignite.services.Service;

public interface EventDispatcher extends Service {
    String SERVICE_NAME = "register.service";


    static void deployService(Ignite ignite) {
        ignite.services().deploy(ServiceConfiguration.clusterSingleton(SERVICE_NAME, new EventDispatcherImpl()));
    }

    static EventDispatcher getService(Ignite ignite) {
        return ignite.services().serviceProxy(EventDispatcher.SERVICE_NAME, EventDispatcher.class, false);
    }
}
