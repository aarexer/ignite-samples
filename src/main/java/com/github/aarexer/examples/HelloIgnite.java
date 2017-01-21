package com.github.aarexer.examples;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;

import java.util.Collections;

public class HelloIgnite {
    public static void main(String[] args) {
        Ignite ignite = Ignition.start(getDefaultConfig());
        IgniteCache<Integer, String> cache = ignite.getOrCreateCache("example");

        for (int i = 1; i <= 100; i++) {
            cache.put(i, Integer.toString(i));
        }
        for (int i = 1; i <= 100; i++) {
            System.out.println("Cache get:" + cache.get(i));
        }
        ignite.close();
    }

    private static TcpDiscoverySpi createDefaultTcpDiscoverySpi() {
        TcpDiscoverySpi spi = new TcpDiscoverySpi();
        TcpDiscoveryMulticastIpFinder tcMp = new TcpDiscoveryMulticastIpFinder();
        tcMp.setAddresses(Collections.singletonList("localhost"));
        spi.setIpFinder(tcMp);

        return spi;
    }

    private static IgniteConfiguration getDefaultConfig() {
        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setClientMode(false);
        cfg.setDiscoverySpi(createDefaultTcpDiscoverySpi());

        return cfg;
    }

}