package com.example.routingapp.route.common.builder;

import lombok.NoArgsConstructor;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class RoutingRequestBuilderIT {

    private static final String ROUTING_ENDPOINT_TEMPLATE = "/routing/%s/%s";

    public static RequestBuilder buildGetAllRequest(final String originCountryCode, final String destinationCountryCode) {
        return MockMvcRequestBuilders.get(format(ROUTING_ENDPOINT_TEMPLATE, originCountryCode, destinationCountryCode));
    }
}
