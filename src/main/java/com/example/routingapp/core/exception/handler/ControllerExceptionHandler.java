package com.example.routingapp.core.exception.handler;

import com.example.routingapp.core.exception.model.RoutingApplicationError;
import com.example.routingapp.route.exception.NoLandCrossingRouteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public RoutingApplicationError handleException(final Exception ex) {
        final String message = ex.getMessage();

        log.error(message, ex);

        return new RoutingApplicationError(INTERNAL_SERVER_ERROR, message);
    }

    @ExceptionHandler(NoLandCrossingRouteException.class)
    @ResponseStatus(BAD_REQUEST)
    public RoutingApplicationError handleNoLandCrossingRouteException(final NoLandCrossingRouteException ex) {
        final String message = ex.getMessage();

        log.error(message, ex);

        return new RoutingApplicationError(BAD_REQUEST, message);
    }
}
