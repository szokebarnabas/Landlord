package com.landlord.domain.person;

import landlord.ddd.pattern.model.ValueObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class PhoneNumber extends ValueObject implements Serializable {

    private final Integer countryCode;
    private final String phoneNumber;
}
