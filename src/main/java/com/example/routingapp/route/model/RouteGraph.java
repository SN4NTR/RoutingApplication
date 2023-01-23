package com.example.routingapp.route.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.emptySet;
import static java.util.Objects.nonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RouteGraph {

    private Map<CountryVertex, Set<CountryVertex>> countriesVertices = new HashMap<>();

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class CountryVertex {

        private String code;
    }

    public void addCountry(final String countryCode) {
        this.countriesVertices.putIfAbsent(new CountryVertex(countryCode), new HashSet<>());
    }

    public void addBorderingCountries(final String countryCode1, final String countryCode2) {
        final CountryVertex countryVertex1 = new CountryVertex(countryCode1);
        final CountryVertex countryVertex2 = new CountryVertex(countryCode2);

        this.countriesVertices.get(countryVertex1).add(countryVertex2);
        this.countriesVertices.get(countryVertex2).add(countryVertex1);
    }

    public Set<CountryVertex> getBorderingCountriesFor(final String countryCode) {
        return nonNull(countryCode)
                ? this.countriesVertices.get(new CountryVertex(countryCode))
                : emptySet();
    }
}
