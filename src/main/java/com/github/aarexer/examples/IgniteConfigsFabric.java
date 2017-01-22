package com.github.aarexer.examples;

import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;

import java.util.Collections;

public class IgniteConfigsFabric {
    private static TcpDiscoverySpi createDefaultTcpDiscoverySpi() {
        TcpDiscoverySpi spi = new TcpDiscoverySpi();
        TcpDiscoveryMulticastIpFinder tcMp = new TcpDiscoveryMulticastIpFinder();
        tcMp.setAddresses(Collections.singletonList("localhost"));
        spi.setIpFinder(tcMp);

        return spi;
    }

    public static IgniteConfiguration getDefaultConfig() {
        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setClientMode(false);
        cfg.setDiscoverySpi(createDefaultTcpDiscoverySpi());

        return cfg;
    }
}
