package com.example.routingapp.route.service;

import com.example.routingapp.country.model.Country;
import com.example.routingapp.country.provider.CountryProvider;
import com.example.routingapp.route.builder.RouteGraphBuilder;
import com.example.routingapp.route.model.Route;
import com.example.routingapp.route.model.RouteGraph;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

import static com.example.routingapp.route.cache.config.RouteCacheConfiguration.ROUTES_CACHE_NAME;
import static com.example.routingapp.route.cache.config.RouteCacheConfiguration.ROUTE_CACHE_MANAGER_NAME;

@AllArgsConstructor
@Validated
@Service
public class RoutingService {

    private final CountryProvider countryProvider;
    private final RouteGraphBuilder routeGraphBuilder;
    private final RouteGraphCalculationService routeGraphCalculationService;

    @Cacheable(cacheNames = ROUTES_CACHE_NAME, cacheManager = ROUTE_CACHE_MANAGER_NAME)
    public Route calculate(@NotNull final String originCountryCode, @NotNull final String destinationCountryCode) {
        final List<Country> countries = countryProvider.provide();
        final RouteGraph routeGraph = routeGraphBuilder.buildForCountries(countries);
        return routeGraphCalculationService.calculate(routeGraph, originCountryCode, destinationCountryCode);
    }
}
