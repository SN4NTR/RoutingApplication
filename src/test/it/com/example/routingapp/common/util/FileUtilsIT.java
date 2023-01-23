package com.example.routingapp.common.util;

import com.example.routingapp.core.exception.RoutingApplicationException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
@Slf4j
public class FileUtilsIT {

    public static String getResourceAsString(final String resourceFile) {
        try (final InputStream inputStream = new ClassPathResource(resourceFile).getInputStream()) {
            return new String(inputStream.readAllBytes(), UTF_8);
        } catch (IOException e) {
            final String message = e.getMessage();
            log.error(message);
            throw new RoutingApplicationException(message);
        }
    }
}
