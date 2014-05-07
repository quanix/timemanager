package com.domac.app.support.cache;

import com.domac.app.testcase.TransactionalTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author : lihaoquan
 */
@ContextConfiguration(locations = {"/applicationContext.xml","/applicationContext-memcached.xml"})
public class MemcachedClientTest extends TransactionalTestCase {

    @Autowired
    private SpyMemcachedClient spyMemcachedClient;

    @Test
    public void normal() {

        String key = "consumer:1";
        String value = "admin";
        spyMemcachedClient.set(key, 60 * 60 * 1, value);
        String result = spyMemcachedClient.get(key);

        System.out.println("result1:"+result);

        spyMemcachedClient.delete(key);
        result = spyMemcachedClient.get(key);

        System.out.println("result2:"+result);

    }

    @Test
    public void safeDelete() {
        String key = "consumer:1";
        spyMemcachedClient.set(key, 60, "admin");
        spyMemcachedClient.safeDelete(key);
        String result = spyMemcachedClient.get(key);
        System.out.println("result:"+result);
    }
}
