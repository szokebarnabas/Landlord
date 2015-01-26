package com.landlord.domain.person;

import landlord.ddd.pattern.model.ValueObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Getter
@RequiredArgsConstructor
public class BirthDate extends ValueObject implements Serializable {

    private final Date birthDate;
}
