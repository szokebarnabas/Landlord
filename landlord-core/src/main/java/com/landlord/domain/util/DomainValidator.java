package com.landlord.domain.util;

import landlord.ddd.pattern.model.DomainException;

public class DomainValidator {

    public static void validateDomainCondition(boolean condition, DomainException exceptionToBeThrown) {
        if (!condition) {
            throw exceptionToBeThrown;
        }
    }
}
