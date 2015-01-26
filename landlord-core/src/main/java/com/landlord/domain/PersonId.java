package com.landlord.domain;

import landlord.ddd.pattern.model.ID;

public class PersonId extends ID {

    protected PersonId(String value) {
        super(value);
    }

    public static PersonId createFrom(String id) {
        return new PersonId(id);
    }

}
