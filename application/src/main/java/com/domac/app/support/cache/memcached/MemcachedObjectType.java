package com.domac.app.support.cache.memcached;

/**
 * @author : lihaoquan
 */
public enum MemcachedObjectType {

    USER("user:", 60 * 60 * 1);

    private String prefix;
    private int expiredTime;

    MemcachedObjectType(String prefix, int expiredTime) {
        this.prefix = prefix;
        this.expiredTime = expiredTime;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getExpiredTime() {
        return expiredTime;
    }
}
