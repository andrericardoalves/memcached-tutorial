package com.alves.config;


import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;

@Configuration
@EnableCaching
public class MemcachedClientConfig {

    @Value("${memcached.config.server}")
    private String server;
    @Value("${memcached.config.port}")
    private Integer port;
    @Value("${memcached.config.username}")
    private String username;
    @Value("${memcached.config.password}")
    private String password;

    @Bean
    public MemcachedClient memcachedClient() throws IOException {
        MemcachedClient mcc = new MemcachedClient(new
                InetSocketAddress(server, port));
        return mcc;
    }

}
