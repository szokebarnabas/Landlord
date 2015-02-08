package com.landlord.app.service.property;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class CreateRoomCommand {
    private final String propertyId;
    private final String roomType;
    private final Integer capacity;
}
