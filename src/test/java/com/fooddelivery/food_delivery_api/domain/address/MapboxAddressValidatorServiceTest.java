package com.fooddelivery.food_delivery_api.domain.address;

import com.fooddelivery.food_delivery_api.infra.Exception.InvalidAddressException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MapboxAddressValidatorServiceTest {

    @Test
    void validate_shouldRejectMissingAddress() {
        var service = new MapboxAddressValidatorService(
                "https://api.mapbox.com/search/geocode/v6/forward",
                "test-token"
        );

        assertThrows(InvalidAddressException.class, () -> service.validate(null));
    }

    @Test
    void validate_shouldRejectInvalidToken() {
        var service = new MapboxAddressValidatorService(
                "https://api.mapbox.com/search/geocode/v6/forward",
                "test-token"
        );

        var address = new RecordAddress(
                "123 Main St",
                "Austin",
                "TX",
                78701,
                "US"
        );

        assertThrows(InvalidAddressException.class, () -> service.validate(address));
    }
}
