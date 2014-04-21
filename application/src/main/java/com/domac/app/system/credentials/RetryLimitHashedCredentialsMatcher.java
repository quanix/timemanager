package com.domac.app.system.credentials;

import com.domac.app.common.util.QueryUtil;
import com.domac.app.system.service.UserRealm;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : lihaoquan
 *
 * 如在1个小时内密码最多重试5次，如果尝试次数超过5次就锁定1小时，1小时后可再次重试，如果还是重试失败，可以锁定如1天，
 * 以此类推，防止密码被暴力破解。我们通过继承HashedCredentialsMatcher，且使用Ehcache记录重试次数和超时时间。
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    CacheManager cacheManager;

    public RetryLimitHashedCredentialsMatcher(String algorithm) {
        super(algorithm);
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        Cache<String, AtomicInteger> passwordRetryCache = cacheManager.getCache("passwordRetryCache");

        String username = (String) token.getPrincipal();
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if(QueryUtil.isEmpty(retryCount)) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }

        System.out.println("retryCount:"+retryCount);

        if(retryCount.incrementAndGet() > 5) {
            //if retry count > 5 throw
            System.out.println("密码错误次数达到5次,请一小时后再尝试登录");
            throw new ExcessiveAttemptsException();
        }

        boolean matches  = super.doCredentialsMatch(token, info);
        if(matches) {
            //clear retry count
            passwordRetryCache.remove(username);
        }
        return matches;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
}
