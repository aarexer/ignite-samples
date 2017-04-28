package com.github.aarexer.examples;

import com.github.aarexer.examples.model.Entity;
import com.github.aarexer.examples.model.EntityKey;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;

public class CacheKeyUpdate {
    private static final String ENTITY_CACHE = "ENTITY_CACHE";

    private Ignite ignite;
    private IgniteCache<EntityKey, Entity> cache;

    public CacheKeyUpdate(Ignite ignite) {
        this.ignite = ignite;
        cache = ignite.getOrCreateCache(ENTITY_CACHE);
    }

    public void putEntity(EntityKey key, Entity value) {
        cache.put(key, value);
    }

    public Entity get(EntityKey key) {
        return cache.get(key);
    }
}
