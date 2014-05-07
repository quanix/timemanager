package com.domac.app.support.cache;

import com.thimbleware.jmemcached.CacheImpl;
import com.thimbleware.jmemcached.Key;
import com.thimbleware.jmemcached.LocalCacheElement;
import com.thimbleware.jmemcached.MemCacheDaemon;
import com.thimbleware.jmemcached.storage.CacheStorage;
import com.thimbleware.jmemcached.storage.hash.ConcurrentLinkedHashMap;
import net.spy.memcached.AddrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author : lihaoquan
 *
 * Memcached服务模拟器
 */
public class MemcachedSimulator implements InitializingBean,DisposableBean {

    private static Logger logger = LoggerFactory.getLogger(MemcachedSimulator.class);

    private String serverUrl = "localhost:11211";

    private MemCacheDaemon<LocalCacheElement> jmemcached;

    private int maxItems = 1024 * 100;
    private long maxBytes = 1024 * 100 * 2048;


    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("Memcached 服务模拟器启动中... ");

        jmemcached = new MemCacheDaemon<LocalCacheElement>();
        CacheStorage<Key,LocalCacheElement> storage = ConcurrentLinkedHashMap.create(
                ConcurrentLinkedHashMap.EvictionPolicy.FIFO,maxItems,maxBytes
        );

        jmemcached.setCache(new CacheImpl(storage));

        jmemcached.setAddr(AddrUtil.getAddresses(serverUrl).get(0));

        jmemcached.start();
        logger.info("Memcached 服务模拟器启动完成 ");
    }

    @Override
    public void destroy() throws Exception {
        logger.info("Memcached 服务模拟器关闭 ");
        jmemcached.stop();
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
}
