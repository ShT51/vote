package com.topjava.vote.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VoteCacheManager {
    
    public static final String RESTAURANT_CACHE = "restaurant_cache";
    public static final String VOTE_CACHE = "vote_cache";
    public static final String CACHE_TTL = "0 0/5 * * * *";
    private static final String CACHE_EVICTED_LOG = "Cache entity: '{}' evicted";
    
    @Scheduled(cron = CACHE_TTL)
    @CacheEvict(cacheNames = {RESTAURANT_CACHE, VOTE_CACHE}, allEntries = true)
    public void clearCacheEntities() {
        log.debug(CACHE_EVICTED_LOG, RESTAURANT_CACHE);
        log.debug(CACHE_EVICTED_LOG, VOTE_CACHE);
    }
}
