package com.landlord.app.service.property;

import com.google.common.base.Preconditions;
import com.landlord.domain.PersonId;
import com.landlord.domain.person.Person;
import com.landlord.domain.person.PersonRepository;
import com.landlord.domain.property.Property;
import com.landlord.domain.property.PropertyId;
import com.landlord.domain.property.PropertyRepository;
import com.landlord.domain.property.PropertyService;
import com.landlord.domain.property.PropertyType;
import com.landlord.domain.property.SharingType;
import com.landlord.domain.property.room.RoomId;
import com.landlord.domain.property.room.RoomType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class PropertyApplicationServiceImpl implements PropertyApplicationService {

    private static final Logger LOG = LoggerFactory.getLogger(PropertyApplicationServiceImpl.class);
    @Inject
    private PropertyService propertyService;
    @Inject
    private PropertyRepository propertyRepository;
    @Inject
    private PersonRepository personRepository;

    @Override
    public void createProperty(CreatePropertyCommand createPropertyCommand) {
        SharingType sharingType = SharingType.valueOf(createPropertyCommand.getSharingType());
        PropertyType propertyType = PropertyType.valueOf(createPropertyCommand.getPropertyType());
        Integer numberOfRooms = createPropertyCommand.getNumberOfRooms();
        propertyService.createProperty(sharingType, propertyType, numberOfRooms);
        LOG.debug("Create new property. Sharing type: {}, property type: {}, number of rooms: {}", sharingType, propertyType, numberOfRooms);
    }

    @Override
    public void createRoom(CreateRoomCommand createRoomCommand) {
        PropertyId propertyId = PropertyId.createFrom(createRoomCommand.getPropertyId());
        Property property = propertyRepository.find(propertyId);
        property.createRoom(RoomType.valueOf(createRoomCommand.getRoomType()), createRoomCommand.getCapacity());
        propertyRepository.update(property);
        LOG.debug("Add new {} room to property {}", createRoomCommand.getRoomType(), property);
    }

    @Override
    public void assignTenantToRoom(AssignTenantToRoomCommand assignTenantToRoomCommand) {
        Preconditions.checkNotNull(assignTenantToRoomCommand.getTenantId());
        Preconditions.checkNotNull(assignTenantToRoomCommand.getPropertyId());
        Preconditions.checkNotNull(assignTenantToRoomCommand.getRoomId());

        PersonId tenantId = PersonId.createFrom(assignTenantToRoomCommand.getTenantId());
        PropertyId propertyId = PropertyId.createFrom(assignTenantToRoomCommand.getPropertyId());
        RoomId roomId = RoomId.createFrom(assignTenantToRoomCommand.getRoomId());

        Person tenant = personRepository.find(tenantId);
        Property property = propertyRepository.find(propertyId);

        if (tenant != null && property != null) {
            property.assignTenantToRoom(tenantId, roomId);
            propertyRepository.update(property);
            LOG.debug("Tenant {} has been assigned to a room", tenant);
        } else {
            throw new RuntimeException("Tenant or property has not found");
        }
    }

    @Override
    public void assignTenantToProperty(AssignTenantToPropertyCommand assignTenantToPropertyCommand) {
        String tenantId = assignTenantToPropertyCommand.getTenantId();
        String propertyId = assignTenantToPropertyCommand.getPropertyId();
        Preconditions.checkNotNull(tenantId);
        Preconditions.checkNotNull(propertyId);

        Person tenant = personRepository.find(PersonId.createFrom(tenantId));
        Property property = propertyRepository.find(PropertyId.createFrom(propertyId));

        if (tenant != null && property != null) {
            property.assignTenant(tenant.getID());
            propertyRepository.update(property);
            LOG.debug("Tenant {} has been assigned to a property", tenant);
        } else {
            throw new RuntimeException("Tenant or property has not found");
        }

    }

    @Override
    public void unassingTenantFromProperty(UnassignTenantFromPropertyCommand unassignTenantFromPropertyCommand) {
        String tenantId = unassignTenantFromPropertyCommand.getTenantId();
        String propertyId = unassignTenantFromPropertyCommand.getPropertyId();
        Preconditions.checkNotNull(tenantId);
        Preconditions.checkNotNull(propertyId);

        Person tenant = personRepository.find(PersonId.createFrom(tenantId));
        Property property = propertyRepository.find(PropertyId.createFrom(propertyId));

        if (tenant != null && property != null) {
            property.unassignTenant(tenant.getID());
            propertyRepository.update(property);
            LOG.debug("Tenant {} has been unassigned from a property", tenant);
        } else {
            throw new RuntimeException("Tenant or property has not found");
        }
    }
}
