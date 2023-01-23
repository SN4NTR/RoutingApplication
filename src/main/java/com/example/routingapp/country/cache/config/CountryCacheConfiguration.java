package com.example.routingapp.country.cache.config;

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
public class CountryCacheConfiguration {

    public static final String COUNTRY_CACHE_MANAGER_NAME = "countryCacheManager";
    public static final String COUNTRIES_CACHE_NAME = "countries";

    @Bean
    public CacheManager countryCacheManager(@NotNull final Caffeine<Object, Object> countryCaffeine) {
        final CaffeineCacheManager defaultCacheManager = new CaffeineCacheManager();
        defaultCacheManager.setCaffeine(countryCaffeine);
        return defaultCacheManager;
    }

    @Bean
    public Caffeine<Object, Object> countryCaffeine(@NotNull final CacheProperties countryCacheProperties) {
        final int expiresAfter = countryCacheProperties.getExpiresAfter();
        final TimeUnit timeUnit = countryCacheProperties.getTimeUnit();
        return Caffeine.newBuilder().expireAfterWrite(expiresAfter, timeUnit);
    }

    @Bean
    public CacheProperties countryCacheProperties(
            @Value("${cache.properties.country.expires-after}") final int expiresAfter,
            @Value("${cache.properties.country.time-unit}") final String timeUnit) {
        final TimeUnit unit = TimeUnit.valueOf(timeUnit);
        return new CacheProperties(expiresAfter, unit);
    }
}
