package com.example.routingapp.route.cache.config;

import com.example.routingapp.core.cache.model.CacheProperties;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

@Configuration
@Validated
public class RouteCacheConfiguration {

    public static final String ROUTE_CACHE_MANAGER_NAME = "routeCacheManager";
    public static final String ROUTES_CACHE_NAME = "routes";

    @Bean
    public CacheManager routeCacheManager(@NotNull final Caffeine<Object, Object> routeCaffeine) {
        final CaffeineCacheManager defaultCacheManager = new CaffeineCacheManager();
        defaultCacheManager.setCaffeine(routeCaffeine);
        return defaultCacheManager;
    }

    @Bean
    public Caffeine<Object, Object> routeCaffeine(@NotNull final CacheProperties routeCacheProperties) {
        final int expiresAfter = routeCacheProperties.getExpiresAfter();
        final TimeUnit timeUnit = routeCacheProperties.getTimeUnit();
        return Caffeine.newBuilder().expireAfterWrite(expiresAfter, timeUnit);
    }

    @Bean
    public CacheProperties routeCacheProperties(
            @Value("${cache.properties.route.expires-after}") final int expiresAfter,
            @Value("${cache.properties.route.time-unit}") final String timeUnit) {
        final TimeUnit unit = TimeUnit.valueOf(timeUnit);
        return new CacheProperties(expiresAfter, unit);
    }
}
