package com.fooddelivery.food_delivery_api.domain.address;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "addresses")
@Entity(name = "Address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Address {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private AddressEntityType entityType;

    private Long entityId;

    private String mapboxId;

    private String label;

    private Boolean isDefault;

    public Address(AddressEntityType entityType, Long entityId, String mapboxId, String label, boolean isDefault) {
        this.entityType = entityType;
        this.entityId = entityId;
        this.mapboxId = mapboxId;
        this.label = label;
        this.isDefault = isDefault;
    }
}
