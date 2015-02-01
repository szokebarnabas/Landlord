package com.landlord.domain.utilities;

import landlord.ddd.pattern.model.ID;

public class UtilityDetailsId extends ID {

    protected UtilityDetailsId(String value) {
        super(value);
    }

    public static UtilityDetailsId createFrom(String id) {
        return new UtilityDetailsId(id);
    }
}
