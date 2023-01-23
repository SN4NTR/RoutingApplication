package com.example.routingapp.core.cache.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CacheProperties {

    private int expiresAfter;

    private TimeUnit timeUnit;
}
