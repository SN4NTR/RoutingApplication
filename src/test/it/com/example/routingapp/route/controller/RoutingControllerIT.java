package com.example.routingapp.route.controller;

import com.example.routingapp.CommonAbstractIT;
import com.example.routingapp.common.util.FileUtilsIT;
import com.example.routingapp.route.common.builder.RoutingRequestBuilderIT;
import com.example.routingapp.route.dto.RouteDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RoutingControllerIT extends CommonAbstractIT {

    private static final String COUNTRIES_RESPONSE_PATH = "/responses/countries.json";

    @Test
    void calculate_givenOriginAndDestinationCountriesNames_shouldReturnRoute() throws Exception {
        // given
        final String originCountryCode = "CZE";
        final String destinationCountryCode = "ITA";
        final RequestBuilder request = RoutingRequestBuilderIT.buildGetAllRequest(originCountryCode, destinationCountryCode);
        final String countriesJsonResponse = FileUtilsIT.getResourceAsString(COUNTRIES_RESPONSE_PATH);

        when(restTemplate.exchange(anyString(), eq(GET), any(HttpEntity.class), any(Class.class))).thenReturn(ResponseEntity.ok(countriesJsonResponse));

        // when
        final ResultActions result = mockMvc.perform(request).andDo(print());

        // then
        result.andExpect(status().isOk());

        final RouteDto routeDto = convertResult(result.andReturn(), RouteDto.class);
        assertThat(routeDto.getRoute()).hasSize(3);
        assertThat(routeDto.getRoute().get(0)).isEqualTo("CZE");
        assertThat(routeDto.getRoute().get(1)).isEqualTo("AUT");
        assertThat(routeDto.getRoute().get(2)).isEqualTo("ITA");
    }

    @Test
    void calculate_givenCountriesNamesWithNoLandCrossingRoute_shouldReturn400() throws Exception {
        // given
        final String originCountryCode = "CZE";
        final String destinationCountryCode = "USA";
        final RequestBuilder request = RoutingRequestBuilderIT.buildGetAllRequest(originCountryCode, destinationCountryCode);
        final String countriesJsonResponse = FileUtilsIT.getResourceAsString(COUNTRIES_RESPONSE_PATH);

        when(restTemplate.exchange(anyString(), eq(GET), any(HttpEntity.class), any(Class.class))).thenReturn(ResponseEntity.ok(countriesJsonResponse));

        // when
        final ResultActions result = mockMvc.perform(request).andDo(print());

        // then
        result.andExpect(status().isBadRequest());
    }
}