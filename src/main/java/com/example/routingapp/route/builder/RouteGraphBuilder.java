package com.example.routingapp.route.builder;

import com.example.routingapp.country.model.Country;
import com.example.routingapp.route.model.RouteGraph;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@Component
public class RouteGraphBuilder {

    public RouteGraph buildForCountries(@NotNull final List<Country> countries) {
        final RouteGraph routeGraph = new RouteGraph();
        countries.forEach(it -> connectCurrentCountryWithBorderingCountriesInGraph(routeGraph, it.getName(), it.getBorders()));
        return routeGraph;
    }

    private void connectCurrentCountryWithBorderingCountriesInGraph(final RouteGraph routeGraph, final String currentCountryCode, final List<String> borderingCountries) {
        routeGraph.addCountry(currentCountryCode);
        borderingCountries.forEach(it -> connectCurrentCountryWithBorderingCountryInGraph(routeGraph, currentCountryCode, it));
    }

    private void connectCurrentCountryWithBorderingCountryInGraph(final RouteGraph routeGraph, final String currentCountryCode, final String borderingCountryCode) {
        routeGraph.addCountry(borderingCountryCode);
        routeGraph.addBorderingCountries(currentCountryCode, borderingCountryCode);
    }
}
