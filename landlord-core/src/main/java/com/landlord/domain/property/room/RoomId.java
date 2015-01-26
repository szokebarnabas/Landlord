package com.landlord.domain.property.room;

import landlord.ddd.pattern.model.ID;

public class RoomId extends ID {

    public RoomId(String value) {
        super(value);
    }

    public static RoomId createFrom(String id) {
        return new RoomId(id);
    }
}
