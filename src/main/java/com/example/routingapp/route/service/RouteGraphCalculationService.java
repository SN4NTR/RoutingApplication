package com.example.routingapp.route.service;

import com.example.routingapp.route.exception.NoLandCrossingRouteException;
import com.example.routingapp.route.model.Route;
import com.example.routingapp.route.model.RouteGraph;
import com.example.routingapp.route.model.RouteGraph.CountryVertex;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;

@Validated
@Service
public class RouteGraphCalculationService {

    public Route calculate(@NotNull final RouteGraph routeGraph, @NotNull final String originCountryCode, @NotNull final String destinationCountryCode) {
        final List<String> countriesInRoute = getCountriesInRoute(routeGraph, originCountryCode, destinationCountryCode);
        validateRoutePossibility(countriesInRoute, originCountryCode, destinationCountryCode);
        return new Route(countriesInRoute);
    }

    private List<String> getCountriesInRoute(final RouteGraph routeGraph, final String originCountryCode, final String destinationCountryCode) {
        // this map is used to avoid visiting the same vertices several times and then trace vertices paths to build final route
        final Map<String, String> visitedCountriesTrace = new HashMap<>();
        final Queue<String> countriesToVisit = new LinkedList<>();

        visitedCountriesTrace.put(originCountryCode, null);
        countriesToVisit.add(originCountryCode);
        boolean isRouteFound = false;

        while (!isRouteFound && !countriesToVisit.isEmpty()) {
            final String currentCountryCode = countriesToVisit.poll();
            final Set<CountryVertex> borderingCountries = routeGraph.getBorderingCountriesFor(currentCountryCode);

            for (CountryVertex borderingCountry : borderingCountries) {
                final String borderingCountryCode = borderingCountry.getCode();
                if (!visitedCountriesTrace.containsKey(borderingCountryCode)) {
                    visitedCountriesTrace.put(borderingCountryCode, currentCountryCode);
                    countriesToVisit.add(borderingCountryCode);

                    if (borderingCountryCode.equals(destinationCountryCode)) {
                        isRouteFound = true;
                        break;
                    }
                }
            }
        }

        return getCountriesByPathsTrace(originCountryCode, destinationCountryCode, visitedCountriesTrace);
    }

    private List<String> getCountriesByPathsTrace(final String originCountryCode, final String destinationCountryCode, final Map<String, String> visitedCountriesTrace) {
        final List<String> route = new ArrayList<>();
        String currentCountryCode = destinationCountryCode;
        route.add(currentCountryCode);

        while (nonNull(currentCountryCode) && !currentCountryCode.equals(originCountryCode)) {
            final String parentCountryCode = visitedCountriesTrace.get(currentCountryCode);
            route.add(parentCountryCode);
            currentCountryCode = parentCountryCode;
        }
        Collections.reverse(route);

        return nonNull(currentCountryCode) ? route : emptyList();
    }

    private void validateRoutePossibility(final List<String> countriesInRoute, final String originCountryCode, final String destinationCountryCode) {
        if (countriesInRoute.isEmpty()) {
            throw new NoLandCrossingRouteException(originCountryCode, destinationCountryCode);
        }
    }
}
