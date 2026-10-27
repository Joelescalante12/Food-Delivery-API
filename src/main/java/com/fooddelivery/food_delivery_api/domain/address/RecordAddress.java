package com.fooddelivery.food_delivery_api.domain.address;

public record RecordAddress(

        String AddressLine1,
        String City,
        String StateProvince,
        Integer PostalCode,
        String Country

) {
    public String toSearchQuery() {
        return String.join(", ",
                AddressLine1 == null ? "" : AddressLine1.trim(),
                City == null ? "" : City.trim(),
                StateProvince == null ? "" : StateProvince.trim(),
                PostalCode == null ? "" : PostalCode.toString(),
                Country == null ? "" : Country.trim()
        ).replaceAll("\\s*,\\s*", ", ").replaceAll("\\s+"," ").trim();
    }
}
