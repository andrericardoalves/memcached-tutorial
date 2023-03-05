package com.alves.config;


import com.google.code.ssm.CacheFactory;
import com.google.code.ssm.config.AbstractSSMConfiguration;
import com.google.code.ssm.config.DefaultAddressProvider;
import com.google.code.ssm.providers.spymemcached.MemcacheClientFactoryImpl;
import com.google.code.ssm.providers.spymemcached.SpymemcachedConfiguration;
import net.spy.memcached.auth.AuthDescriptor;
import net.spy.memcached.auth.PlainCallbackHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class MemCachierConfig extends AbstractSSMConfiguration {

    @Value("${memcached.config.server}")
    private String server;
    @Value("${memcached.config.port}")
    private String port;

    @Value("${memcached.config.username}")
    private String userName;

    @Value("${memcached.config.password}")
    private String password;

    @Bean
    @Override
    public CacheFactory defaultMemcachedClient() {
        String memCachierServer = server.concat(":").concat(port);
        AuthDescriptor ad = new AuthDescriptor(new String[] { "PLAIN" },
                new PlainCallbackHandler(userName, password));

        final SpymemcachedConfiguration conf = new SpymemcachedConfiguration();
        conf.setUseBinaryProtocol(true);
        conf.setAuthDescriptor(ad);

        final CacheFactory cf = new CacheFactory();
        cf.setCacheClientFactory(new MemcacheClientFactoryImpl());
        cf.setAddressProvider(new DefaultAddressProvider(memCachierServer));
        cf.setConfiguration(conf);
        return cf;
    }


}
