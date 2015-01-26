package com.landlord.domain.property.utilities;

import com.landlord.domain.Address;
import landlord.ddd.pattern.model.Entity;

public class UtilityProvider extends Entity<UtilityProviderId> {

    private UtilityType utilityType;
    private String providerName;
    private Address address;
    private String meterNumber;
    private String referenceNumber;

    protected UtilityProvider(UtilityProviderId id) {
        super(id);
    }
}
