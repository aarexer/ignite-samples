package com.github.aarexer.examples;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;

import static com.github.aarexer.examples.IgniteConfigsFabric.getDefaultConfig;

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


}