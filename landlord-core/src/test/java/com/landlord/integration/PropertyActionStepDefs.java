package com.landlord.integration;

import com.landlord.domain.PersonId;
import com.landlord.domain.property.Property;
import com.landlord.domain.property.PropertyFactory;
import com.landlord.domain.property.PropertyType;
import com.landlord.domain.property.SharingType;
import com.landlord.domain.property.room.Room;
import com.landlord.infrastructure.service.JUGIDGenerationStrategy;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

import static org.junit.Assert.assertTrue;

public class PropertyActionStepDefs extends PropertyStepDefs {

    private PropertyFactory propertyFactory;

    @Before
    public void init() {
        propertyFactory = new PropertyFactory();
        propertyFactory.setIdGenerator(new JUGIDGenerationStrategy());
    }

    @Given("^I create a '(.*)' property '(.*)' with the following rooms$")
    public void i_create_a_property_with_the_following_rooms(SharingType sharingType, String propertyName, List<RoomDto> rooms) {
        Property property = propertyFactory.createProperty(propertyName, sharingType, PropertyType.HOUSE);
        properties.add(property);
        for (RoomDto room : rooms) {
            property.createRoom(room.getRoomName(), room.getRoomType(), room.getNumberOfBeds());
        }
    }

    @Given("^I add tenant '(.+)' to '(.+)'$")
    public void add_tenant_to_property(String tenantName, String propertyName) {
        Property property = findPropertyByName(propertyName);
        property.assignTenant(PersonId.createFrom(tenantName));
    }

    @Given("^I assign '(.+)' to room '(.+)' in '(.+)'$")
    public void assign_tenant_to_room(String tenantName, String roomName, String propertyName) {
        Property property = findPropertyByName(propertyName);
        property.assignTenantToRoom(PersonId.createFrom(tenantName), findRoomIdByName(roomName, propertyName));
    }

    @Then("^'(.+)' is assigned to room '(.+)' in '(.+)'$")
    public void tenant_is_assigned_to_room(String tenantName, String roomName, String propertyName) {
        Set<Room> rooms = findPropertyByName(propertyName).rooms();
        Optional<Room> x = rooms.stream()
                .filter(room -> room.roomName().equals(roomName))
                .findFirst()
                .filter(room -> room.tenants().contains(PersonId.createFrom(tenantName)));
        assertTrue(x.isPresent());
    }


    @Then("^The following rooms are free: '(.+)' in '(.+)'$")
    public void the_following_rooms_are_free(List<String> roomNames, String propertyName) {
        Property property = findPropertyByName(propertyName);
        Set<Room> freeRooms = property.findFreeRooms();
        roomNames.forEach(new Consumer<String>() {
            @Override
            public void accept(String roomName) {
                assertTrue(freeRooms.stream().anyMatch(room -> room.roomName().equals(roomName)));
            }
        });
    }
}
