package com.example.routingapp.core.web.converter;

import com.example.routingapp.core.exception.RoutingApplicationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
@Slf4j
public class JsonConverter {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> T fromJson(final String jsonBody, final TypeReference<T> typeReference) {
        try {
            return OBJECT_MAPPER.readValue(jsonBody, typeReference);
        } catch (JsonProcessingException e) {
            final String message = e.getMessage();
            log.error(message);
            throw new RoutingApplicationException(message);
        }
    }

    public static <T> T fromJson(String json, Class<T> tClass) {
        try {
            return OBJECT_MAPPER.readValue(json, tClass);
        } catch (Exception e) {
            final String message = e.getMessage();
            log.error(message);
            throw new RoutingApplicationException(message);
        }
    }
}
