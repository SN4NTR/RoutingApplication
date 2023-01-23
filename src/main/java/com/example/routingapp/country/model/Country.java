package com.example.routingapp.country.model;

import com.example.routingapp.country.dto.CountryDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Country {

    private String name;

    private List<String> borders;

    public Country(final CountryDto countryDto) {
        this.name = countryDto.getCca3();
        this.borders = countryDto.getBorders();
    }
}
