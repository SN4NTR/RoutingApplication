package com.example.routingapp.country.provider;

import com.example.routingapp.core.web.converter.JsonConverter;
import com.example.routingapp.country.dto.CountryDto;
import com.example.routingapp.country.model.Country;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.example.routingapp.core.web.util.RequestUtils.createEmptyRequest;
import static com.example.routingapp.country.cache.config.CountryCacheConfiguration.COUNTRIES_CACHE_NAME;
import static com.example.routingapp.country.cache.config.CountryCacheConfiguration.COUNTRY_CACHE_MANAGER_NAME;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpMethod.GET;

@AllArgsConstructor
@Component
public class CountryProvider {

    private static final String COUNTRIES_DATA_URL = "https://raw.githubusercontent.com/mledoze/countries/master/countries.json";

    private final RestTemplate restTemplate;

    @Cacheable(cacheNames = COUNTRIES_CACHE_NAME, cacheManager = COUNTRY_CACHE_MANAGER_NAME)
    public List<Country> provide() {
        final ResponseEntity<String> response = restTemplate.exchange(COUNTRIES_DATA_URL, GET, createEmptyRequest(), String.class);
        final String jsonBody = response.getBody();
        return convertResponseToModel(jsonBody);
    }

    private List<Country> convertResponseToModel(final String jsonBody) {
        final List<CountryDto> countriesDtos = JsonConverter.fromJson(jsonBody, new TypeReference<>() {});
        return countriesDtos.stream()
                .map(Country::new)
                .collect(toList());
    }
}
