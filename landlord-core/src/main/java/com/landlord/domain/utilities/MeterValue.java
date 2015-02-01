package com.landlord.domain.utilities;

import landlord.ddd.pattern.model.ValueObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Getter
@RequiredArgsConstructor
class MeterValue extends ValueObject implements Serializable {

    private final Date occured;
    private final Double meterNumber;
}
