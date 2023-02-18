package com.alves;

import net.spy.memcached.MemcachedClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MemCachedTest {

    @Autowired
    MemcachedClient memcachedClient;

    @Test
    public void shouldSaveInMemcached() {

        log("=====> Approach-1: SpyMemcached <=====\n");
        log("==> Connected to Crunchify's Local Server Successfully." + " Host: localhost, Port: 11211");

        memcachedClient.set("Crunchify", 2000, "New York");
        memcachedClient.set("Google", 2000, "Mountain View");
        memcachedClient.set("PayPal", 2000, "San Jose");
        memcachedClient.set("Twitter", 2000, "San Francisco");
        memcachedClient.set("Amazon", 2000, "Seattle");
        log("==> Total 5 Records added to MemCached using net.spy.spymemcached method\n");
        // Get with a single key and decode using the default transcoder.
        assertEquals("Mountain View",  memcachedClient.get("Google"));
        assertEquals("San Jose", memcachedClient.get("PayPal"));
        assertEquals("San Francisco", memcachedClient.get("Twitter"));
        assertEquals("Seattle", memcachedClient.get("Amazon"));
        assertEquals("New York", memcachedClient.get("Crunchify"));
        log("==> Total 5 Records Retrieved from MemCached using net.spy.spymemcached method\n");
        // Delete the given key from the cache.
        memcachedClient.delete("Crunchify");
        log("==> Key:Crunchify deleted successfully\n");

        assertEquals(null, memcachedClient.get("Crunchify"));
    }

    private void log(Object object) {
        System.out.println(object);
    }
}
