package com.github.aarexer.examples;

import com.github.aarexer.examples.model.Entity;
import com.github.aarexer.examples.model.EntityKey;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.junit.Before;
import org.junit.Test;

public class CachePutTest {
    private Ignite ignite;
    private CacheKeyUpdate cacheKeyUpdate;

    @Before
    public void setUp() throws Exception {
        ignite = Ignition.start("ignite-server.xml");
        cacheKeyUpdate = new CacheKeyUpdate(ignite);
    }

    @Test
    public void putEntity() throws Exception {
        cacheKeyUpdate.putEntity(new EntityKey("One", 1, 0), new Entity("Prop1"));
    }
}
