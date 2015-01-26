package com.landlord.integration;

import com.google.common.collect.Sets;
import com.landlord.domain.property.Property;
import com.landlord.domain.property.room.Room;
import com.landlord.domain.property.room.RoomId;

import java.util.Optional;
import java.util.Set;

public class PropertyStepDefs {
    protected Set<Property> properties;

    public PropertyStepDefs() {
        properties = Sets.newHashSet();
    }

    protected Property findPropertyByName(String name) {
        Optional<Property> result = properties.stream().filter(x -> x.propertyName().equals(name)).findFirst();
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException("Property not found: " + name);
        }
    }

    protected RoomId findRoomIdByName(String roomName, String propertyName) {
        Property property = findPropertyByName(propertyName);
        Optional<Room> room = property.rooms().stream().filter(x -> x.roomName().equals(roomName)).findFirst();
        if (room.isPresent()) {
            return room.get().getID();
        } else {
            throw new RuntimeException("Room not found: " + roomName);
        }
    }
}
