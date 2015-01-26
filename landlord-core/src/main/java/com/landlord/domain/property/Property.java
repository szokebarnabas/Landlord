package com.landlord.domain.property;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.landlord.domain.Address;
import com.landlord.domain.Notes;
import com.landlord.domain.PersonId;
import com.landlord.domain.property.room.Room;
import com.landlord.domain.property.room.RoomId;
import com.landlord.domain.property.room.RoomType;
import com.landlord.domain.property.utilities.UtilityDetails;
import landlord.ddd.pattern.idgeneration.IDGenerator;
import landlord.ddd.pattern.model.AggregateRoot;
import landlord.ddd.pattern.model.DomainException;

import javax.inject.Inject;
import java.text.MessageFormat;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import static com.landlord.domain.util.DomainValidator.validateDomainCondition;
import static java.util.stream.Collectors.toSet;

public class Property extends AggregateRoot<PropertyId> {

    private Set<PersonId> tenants = Sets.newHashSet();
    private PropertyType propertyType;
    private String propertyName;
    private Integer numberOfRooms;
    private PropertyStatus propertyStatus;
    private Address propertyAddress;
    private PersonId owner;
    private Furnishing furnishing;
    private SharingType sharingType;
    private Notes notes;
    private Set<Image> imageList;
    private Set<Room> rooms;
    private UtilityDetails utilityDetails;
    private IDGenerator<String> idGenerator;

    public Property(PropertyId id, String propertyName, SharingType sharingType, PropertyType propertyType) {
        super(id);
        Preconditions.checkNotNull(id);
        Preconditions.checkNotNull(propertyType);
        this.propertyType = propertyType;
        this.propertyName = propertyName;
        this.propertyStatus = PropertyStatus.LET_AGREED;
        this.sharingType = sharingType;
        this.rooms = Sets.newHashSet();
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String propertyName() {
        return propertyName;
    }

    public void assignTenant(final PersonId tenantId) {
        validateDomainCondition(!tenantIds().contains(tenantId), new DomainException(this.getID(), MessageFormat.format("Tenant {0} is already assigned to the property", tenantId)));
        tenants.add(tenantId);
    }

    public void unassignTenant(PersonId tenantId) {
        validateDomainCondition(tenantIds().contains(tenantId), new DomainException(this.getID(), MessageFormat.format("Tenant id {0} not found", tenantId)));
        tenants.remove(tenantId);
    }

    public Set<PersonId> tenantIds() {
        return tenants;
    }

    public SharingType sharingType() {
        return sharingType;
    }

    public PropertyStatus propertyStatus() {
        return propertyStatus;
    }

    public PropertyType propertyType() {
        return propertyType;
    }

    public void modifyPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    @Inject
    public void setIdGenerator(IDGenerator<String> idGenerator) {
        this.idGenerator = idGenerator;
    }

    @VisibleForTesting
    protected void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public void createRoom(String roomName, RoomType roomType, Integer roomCapacity) {
        Preconditions.checkNotNull(roomCapacity);
        Preconditions.checkArgument(roomCapacity > 0, "Room capacity must be greater than 0");
        rooms.add(Room.with(RoomId.createFrom(idGenerator.generate()), roomName, roomType, roomCapacity));
    }

    public void createRoom(RoomType roomType, Integer roomCapacity) {
        createRoom("", roomType, roomCapacity);
    }

    public Set<Room> rooms() {
        return this.rooms;
    }

    public void assignTenantToRoom(PersonId tenantId, RoomId roomId) {
        Optional<Room> roomOptional = findRoomById(roomId);
        if (roomOptional.isPresent()) {
            validateDomainCondition(tenants.contains(tenantId), new DomainException(this.getID(), MessageFormat.format("Tenant {0} does not belong to the property", tenantId)));
            Room room = roomOptional.get();
            room.assign(tenantId);
        } else {
            throw new DomainException(roomId, MessageFormat.format("Room with id {0} not found", roomId));
        }
    }

    public void unassignTenantFromRoom(PersonId tenantId, RoomId roomId) {
        Optional<Room> roomOptional = findRoomById(roomId);
        if (roomOptional.isPresent()) {
            validateDomainCondition(tenants.contains(tenantId), new DomainException(this.getID(), MessageFormat.format("Tenant {0} does not belong to the property", tenantId)));
            Room room = roomOptional.get();
            room.unassign(tenantId);
        } else {
            throw new DomainException(roomId, MessageFormat.format("Room with id {0} not found", roomId));
        }
    }

    private Optional<Room> findRoomById(final RoomId roomId) {
        return rooms.stream().filter(room -> room.getID().equals(roomId)).findFirst();
    }

    public Set<Room> findFreeRooms() {
        return rooms().stream().filter(new Predicate<Room>() {
            @Override
            public boolean test(Room room) {
                return room.numberOfTenants() == 0;
            }
        }).collect(toSet());
    }
}
