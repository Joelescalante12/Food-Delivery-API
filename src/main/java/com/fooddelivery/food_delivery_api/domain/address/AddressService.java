package com.fooddelivery.food_delivery_api.domain.address;

import com.fooddelivery.food_delivery_api.infra.Exception.InvalidAddressException;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private static final int MAX_LABEL_LENGTH = 50;
    private static final int MAX_MAPBOX_ID_LENGTH = 1024;

    private final MapboxAddressValidatorService mapboxAddressValidatorService;
    private final AddressRepository addressRepository;

    public AddressService(
            MapboxAddressValidatorService mapboxAddressValidatorService,
            AddressRepository addressRepository
    ) {
        this.mapboxAddressValidatorService = mapboxAddressValidatorService;
        this.addressRepository = addressRepository;
    }

    /**
     * Validates the address against Mapbox and returns its Mapbox id.
     * Call this before persisting the owner so invalid addresses are rejected early.
     */
    public String resolveMapboxId(RecordAddress address) {
        if (address != null && address.label() != null && address.label().length() > MAX_LABEL_LENGTH) {
            throw new InvalidAddressException("Address label must have at most " + MAX_LABEL_LENGTH + " characters.");
        }
        String mapboxId = mapboxAddressValidatorService.resolveMapboxId(address);
        if (mapboxId.length() > MAX_MAPBOX_ID_LENGTH) {
            throw new InvalidAddressException("The address is too long to be stored. Please shorten it.");
        }
        return mapboxId;
    }

    public Address create(AddressEntityType entityType, Long entityId, String mapboxId, String label) {
        // The owner has just been created, so this is its first (default) address.
        return addressRepository.save(new Address(entityType, entityId, mapboxId, label, true));
    }
}
