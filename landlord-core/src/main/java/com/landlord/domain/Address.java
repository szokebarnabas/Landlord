package com.landlord.domain;

import landlord.ddd.pattern.model.ValueObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Builder;

import java.io.Serializable;

@Getter
@Builder
@RequiredArgsConstructor
public class Address extends ValueObject implements Serializable {

    private final String country;
    private final String city;
    private final String postCode;
    private final String street;
    private final String houseNumber;
    private final String floor;
    private final String door;
    private final String otherComment;
}
