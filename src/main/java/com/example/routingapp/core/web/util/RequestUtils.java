package com.example.routingapp.core.web.util;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class RequestUtils {

    public static HttpEntity<Object> createEmptyRequest() {
        return new HttpEntity<>(null, null);
    }
}
