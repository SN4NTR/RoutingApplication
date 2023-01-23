package com.example.routingapp.route.exception;

import com.example.routingapp.core.exception.RoutingApplicationException;

import static java.lang.String.format;

public class NoLandCrossingRouteException extends RoutingApplicationException {

    private static final String ERROR_MESSAGE_TEMPLATE = "There is not land-crossing route between %s and %s.";

    public NoLandCrossingRouteException(final String originCountryCode, final String destinationCountryCode) {
        super(format(ERROR_MESSAGE_TEMPLATE, originCountryCode, destinationCountryCode));
    }
}
