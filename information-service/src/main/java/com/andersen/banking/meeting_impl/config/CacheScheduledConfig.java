package com.andersen.banking.meeting_impl.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class CacheScheduledConfig {

    private final CacheManager cacheManager;

    @Value("${cache.name.exchange-rates}")
    private String nameCache;

    public CacheScheduledConfig(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    private void evictAllCaches() {
        cacheManager.getCacheNames()
                .stream().filter(cache -> cache.equals(nameCache))
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }

    @Scheduled(cron = "${cache.scheduler.exchange-rates}")
    public void evictAllCachesAtIntervals() {
        evictAllCaches();
    }
}
