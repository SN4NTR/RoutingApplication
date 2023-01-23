package com.example.routingapp.route.controller;

import com.example.routingapp.route.dto.RouteDto;
import com.example.routingapp.route.model.Route;
import com.example.routingapp.route.service.RoutingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/routing")
@AllArgsConstructor
@RestController
public class RoutingController {

    private final RoutingService routingService;

    @GetMapping("/{originCountryCode}/{destinationCountryCode}")
    public RouteDto calculate(@PathVariable final String originCountryCode, @PathVariable final String destinationCountryCode) {
        final Route route = routingService.calculate(originCountryCode, destinationCountryCode);
        return new RouteDto(route);
    }
}
