package com.example.routingapp.core.exception.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class RoutingApplicationError {

    private LocalDateTime timestamp;

    private Integer status;

    private String error;

    private Object message;

    public RoutingApplicationError(HttpStatus httpStatus, String message) {
        this.timestamp = LocalDateTime.now(Clock.systemUTC());
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.message = message;
    }

    public RoutingApplicationError(HttpStatus httpStatus, List<String> messages) {
        this.timestamp = LocalDateTime.now(Clock.systemUTC());
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.message = messages;
    }
}
