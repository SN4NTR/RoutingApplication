package com.example.routingapp.core.cache.config;

import com.example.routingapp.core.cache.model.CacheProperties;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
@Validated
public class CacheConfiguration {

    public static final String DEFAULT_CACHE_MANAGER_NAME = "defaultCacheManager";

    @Primary
    @Bean
    public CacheManager defaultCacheManager(@NotNull final Caffeine<Object, Object> defaultCaffeine) {
        final CaffeineCacheManager defaultCacheManager = new CaffeineCacheManager();
        defaultCacheManager.setCaffeine(defaultCaffeine);
        return defaultCacheManager;
    }

    @Bean
    public Caffeine<Object, Object> defaultCaffeine(@NotNull final CacheProperties defaultCacheProperties) {
        final int expiresAfter = defaultCacheProperties.getExpiresAfter();
        final TimeUnit timeUnit = defaultCacheProperties.getTimeUnit();
        return Caffeine.newBuilder().expireAfterWrite(expiresAfter, timeUnit);
    }

    @Bean
    public CacheProperties defaultCacheProperties(
            @Value("${cache.properties.default.expires-after}") final int expiresAfter,
            @Value("${cache.properties.default.time-unit}") final String timeUnit) {
        final TimeUnit unit = TimeUnit.valueOf(timeUnit);
        return new CacheProperties(expiresAfter, unit);
    }
}
