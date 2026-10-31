package com.fooddelivery.food_delivery_api.domain.address;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MapboxGeocodeResponse(List<MapboxFeature> features) {
}

@JsonIgnoreProperties(ignoreUnknown = true)
record MapboxFeature(String id, MapboxProperties properties) {
}

@JsonIgnoreProperties(ignoreUnknown = true)
record MapboxProperties(
        String mapbox_id,
        String full_address,
        String name,
        String place_formatted,
        String address,
        String city,
        String region,
        String country,
        String postcode
) {
}
