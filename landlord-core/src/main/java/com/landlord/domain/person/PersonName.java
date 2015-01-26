package com.landlord.domain.person;

import landlord.ddd.pattern.model.ValueObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class PersonName extends ValueObject implements Serializable {

    private final String firstName;
    private final String middleName;
    private final String lastName;

    public PersonName(String firstName, String lastName) {
        this.firstName = firstName;
        this.middleName = null;
        this.lastName = lastName;
    }
}
