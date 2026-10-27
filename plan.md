# Project status plan

## Current status
The project is an early-stage Spring Boot food-delivery API with core customer, restaurant, menu, and order flows already implemented. A Mapbox-based address validation feature was added to the customer creation flow and is now wired through the service layer with HTTP 400 handling for invalid addresses.

## Recent work
- Added a `MapboxAddressValidatorService` for validating structured address data against Mapbox Geocoding API v6.
- Added an `InvalidAddressException` and a controller advice for returning HTTP 400 on invalid addresses.
- Wired validation into `CustomerService.createCustomer` before saving the customer.
- Externalized Mapbox configuration into `application.properties`.
- Added a focused validation test for the Mapbox address flow.

## Next steps
1. Review and clean the repo-level status/address inconsistencies that still exist outside the validation path.
2. Decide whether structured address storage should become a first-class entity/table or remain flattened into the customer string field.
3. Add broader controller/service tests for customer creation success and failure cases.
4. Continue with the project’s next feature area after the current validation work is stable.

## Notes
- The address validation feature preserves the existing architecture: controller -> service -> repository, with business validation in the service layer.
- The implementation keeps Mapbox config external and does not refactor unrelated code.
- The project still has several older naming and modeling inconsistencies that are not part of this validation patch.
