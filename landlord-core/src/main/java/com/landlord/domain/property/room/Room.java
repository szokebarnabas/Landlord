package com.landlord.domain.property.room;

import com.google.common.collect.Sets;
import com.landlord.domain.Notes;
import com.landlord.domain.PersonId;
import landlord.ddd.pattern.model.DomainException;
import landlord.ddd.pattern.model.Entity;

import java.text.MessageFormat;
import java.util.Set;

public class Room extends Entity<RoomId> {

    private RoomType roomType;
    private Set<PersonId> tenants;
    private String roomNumber;
    private String floor;
    private Notes notes;
    private Integer numberOfBeds;
    private String roomName;

    private Room(RoomId id, RoomType roomType, String roomName, Set<PersonId> tenants, Integer numberOfBeds) {
        super(id);
        this.roomType = roomType;
        this.tenants = tenants;
        this.numberOfBeds = numberOfBeds;
        this.roomName = roomName;
    }

    public static Room with(RoomId id, RoomType roomType, Integer roomCapacity) {
        return new Room(id, roomType, id.toString(), Sets.newHashSet(), roomCapacity);
    }

    public static Room with(RoomId id, String roomName, RoomType roomType, Integer roomCapacity) {
        return new Room(id, roomType, roomName, Sets.newHashSet(), roomCapacity);
    }

    public static Room with(RoomId id, String roomName, RoomType roomType, Integer roomCapacity, Set<PersonId> tenants) {
        return new Room(id, roomType, roomName, tenants, roomCapacity);
    }

    public void assign(PersonId personId) {
        if (tenants.contains(personId)) {
            throw new DomainException(getID(), MessageFormat.format("Tenant {0} is already assigned to the room", personId));
        }
        if (numberOfTenants() >= numberOfBeds()) {
            throw new DomainException(getID(), MessageFormat.format("The maximum capacity of the room is {0}", numberOfBeds()));
        }
        tenants.add(personId);
    }

    public Integer numberOfBeds() {
        return numberOfBeds;
    }

    public void unassign(PersonId personId) {
        if (tenants.contains(personId)) {
            tenants.remove(personId);
        } else {
            throw new DomainException(getID(), MessageFormat.format("Tenant {0} is not in the room", personId));
        }
    }

    public void modifyNotes(Notes notes) {
        this.notes = notes;
    }

    public Notes notes() {
        return notes;
    }

    public Set<PersonId> tenants() {
        return tenants;
    }

    public int numberOfTenants() {
        return tenants().size();
    }

    public RoomType roomType() {
        return roomType;
    }

    public void modifyRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String roomNumber() {
        return roomNumber;
    }

    public void modifyFloorNumber(String floorNumber) {
        this.floor = floorNumber;
    }

    public String floorNumber() {
        return floor;
    }

    public String roomName() {
        return roomName;
    }
}
