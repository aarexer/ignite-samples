package com.github.aarexer.dispatcher;

import org.apache.ignite.services.Service;
import org.jetbrains.annotations.NotNull;

public final class ServiceConfiguration {
    public static org.apache.ignite.services.ServiceConfiguration clusterSingleton(String name, Service service) {
        org.apache.ignite.services.ServiceConfiguration serviceConfiguration = getServiceConfiguration(name, service);
        serviceConfiguration.setMaxPerNodeCount(1);
        serviceConfiguration.setTotalCount(1);

        return serviceConfiguration;
    }

    public static org.apache.ignite.services.ServiceConfiguration nodeSingleton(String name, Service service) {
        org.apache.ignite.services.ServiceConfiguration serviceConfiguration = getServiceConfiguration(name, service);
        serviceConfiguration.setMaxPerNodeCount(1);
        serviceConfiguration.setTotalCount(0);

        return serviceConfiguration;
    }

    public static org.apache.ignite.services.ServiceConfiguration create(String name, Service service, int maxPerNode, int maxInstances) {
        org.apache.ignite.services.ServiceConfiguration serviceConfiguration = getServiceConfiguration(name, service);
        serviceConfiguration.setMaxPerNodeCount(maxPerNode);
        serviceConfiguration.setTotalCount(maxInstances);

        return serviceConfiguration;
    }

    @NotNull
    private static org.apache.ignite.services.ServiceConfiguration getServiceConfiguration(String name, Service service) {
        org.apache.ignite.services.ServiceConfiguration serviceConfiguration = new org.apache.ignite.services.ServiceConfiguration();
        serviceConfiguration.setName(name);
        serviceConfiguration.setService(service);
//        serviceConfiguration.setNodeFilter(new NodeFilter(NodeRole.WORKER_NODE));
        return serviceConfiguration;
    }
}