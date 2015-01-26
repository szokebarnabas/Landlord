package com.landlord.domain;

import landlord.ddd.pattern.model.ValueObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class Notes extends ValueObject implements Serializable {
    private String notes = "";
}
