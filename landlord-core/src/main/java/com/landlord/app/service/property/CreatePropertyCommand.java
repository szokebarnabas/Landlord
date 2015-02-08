package com.landlord.app.service.property;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class CreatePropertyCommand {
    private final String sharingType;
    private final String propertyType;
    private final Integer numberOfRooms;
}
