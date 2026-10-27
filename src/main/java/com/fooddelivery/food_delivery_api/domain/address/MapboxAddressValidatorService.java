package com.fooddelivery.food_delivery_api.domain.address;

import com.fooddelivery.food_delivery_api.infra.Exception.InvalidAddressException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Service
public class MapboxAddressValidatorService {

    private final RestClient restClient;
    private final String mapboxAccessToken;

    public MapboxAddressValidatorService(
            @Value("${mapbox.api.base-url:https://api.mapbox.com/search/geocode/v6/forward}") String mapboxBaseUrl,
            @Value("${mapbox.api.access-token:${MAPBOX_PUBLIC_TOKEN:}}") String mapboxAccessToken
    ) {
        this.mapboxAccessToken = mapboxAccessToken;
        this.restClient = RestClient.builder()
                .baseUrl(mapboxBaseUrl)
                .build();
    }

    public void validate(RecordAddress address) {
        if (address == null) {
            throw new InvalidAddressException("Address is required.");
        }

        String query = buildQuery(address);
        if (query.isBlank()) {
            throw new InvalidAddressException("Address is incomplete.");
        }

        try {
            MapboxGeocodeResponse response = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("q", query)
                            .queryParam("types", "address")
                            .queryParam("limit", 1)
                            .queryParam("access_token", mapboxAccessToken)
                            .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .body(MapboxGeocodeResponse.class);

            if (response == null || response.features() == null || response.features().isEmpty()) {
                throw new InvalidAddressException("The delivery address does not exist or could not be validated.");
            }
        } catch (HttpClientErrorException.Unauthorized exception) {
            throw new InvalidAddressException("Mapbox address validation failed because the configured token is invalid or expired.");
        } catch (HttpClientErrorException exception) {
            throw new InvalidAddressException("Mapbox address validation failed: " + exception.getStatusCode());
        } catch (RestClientException exception) {
            throw new InvalidAddressException("Mapbox address validation service is currently unavailable.");
        }
    }

    private String buildQuery(RecordAddress address) {
        return address.toSearchQuery();
    }
}
