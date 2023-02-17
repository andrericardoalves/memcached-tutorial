package com.alves.config;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.ssm.CacheFactory;
import com.google.code.ssm.config.AbstractSSMConfiguration;
import com.google.code.ssm.config.DefaultAddressProvider;
import com.google.code.ssm.providers.xmemcached.XMemcachedConfiguration;
import com.google.code.ssm.providers.xmemcached.MemcacheClientFactoryImpl;
import net.rubyeye.xmemcached.auth.AuthInfo;
import net.rubyeye.xmemcached.utils.AddrUtil;

@Configuration
public class MemcacheConfig extends AbstractSSMConfiguration {

    @Value("${memcache.config.server}")
    private String server;
    @Value("${memcache.config.username}")
    private String username;
    @Value("${memcache.config.password}")
    private String password;

    @Bean
    @Override
    public CacheFactory defaultMemcachedClient() {
        String serverString = server.replace(",", " ");
        List<InetSocketAddress> servers = AddrUtil.getAddresses(serverString);
        AuthInfo authInfo = AuthInfo.plain(username,  password);
        Map<InetSocketAddress, AuthInfo> authInfoMap =
                new HashMap<InetSocketAddress, AuthInfo>();
        for(InetSocketAddress server : servers) {
            authInfoMap.put(server, authInfo);
        }

        final XMemcachedConfiguration conf = new XMemcachedConfiguration();
        conf.setUseBinaryProtocol(true);
        conf.setAuthInfoMap(authInfoMap);

        final CacheFactory cf = new CacheFactory();
        cf.setCacheClientFactory(new MemcacheClientFactoryImpl());
        cf.setAddressProvider(new DefaultAddressProvider(serverString));
        cf.setConfiguration(conf);
        return cf;
    }
}
