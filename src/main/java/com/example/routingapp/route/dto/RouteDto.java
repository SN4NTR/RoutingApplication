package com.example.routingapp.route.dto;

import com.example.routingapp.route.model.Route;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RouteDto {

    private List<String> route;

    public RouteDto(final Route route) {
        this.route = route.getRoute();
    }
}
