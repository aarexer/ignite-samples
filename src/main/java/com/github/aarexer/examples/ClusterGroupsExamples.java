package com.github.aarexer.examples;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.Ignition;
import org.apache.ignite.cluster.ClusterGroup;

import static com.github.aarexer.examples.IgniteConfigsFabric.getDefaultConfig;

public class ClusterGroupsExamples {
    public static void main(String[] args) {
        Ignite ignite = Ignition.start(getDefaultConfig());
        ClusterGroup cg = ignite.cluster().forClients();
        IgniteCompute clientCompute = ignite.compute(cg);
        clientCompute.broadcast(() -> System.out.println("Hello, and sum: " + (2 + 2)));
    }
}
