package com.landlord.domain.property;

import com.google.common.collect.Sets;
import com.landlord.domain.PersonId;
import com.landlord.domain.property.room.Room;
import com.landlord.domain.property.room.RoomId;
import com.landlord.domain.property.room.RoomType;
import landlord.ddd.pattern.idgeneration.IDGenerator;
import landlord.ddd.pattern.model.DomainException;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PropertyTest {

    private Property testObject;

    @Mock
    private Room room1;
    @Mock
    private Room room2;
    @Mock
    private Room room3;
    @Mock
    private RoomId roomIdMock;
    @Mock
    private PersonId personId1Mock;
    @Mock
    private PersonId personId2Mock;

    @Before
    public void init() {
        testObject = new Property(PropertyId.createFrom("property_1"), "", SharingType.SHARED, PropertyType.HOUSE, 3);
        testObject.setIdGenerator(mock(IDGenerator.class));
        when(room1.getID()).thenReturn(RoomId.createFrom("room1"));
        when(room2.getID()).thenReturn(RoomId.createFrom("room2"));
        when(room3.getID()).thenReturn(RoomId.createFrom("room3"));
        when(room1.numberOfTenants()).thenReturn(2);
        when(room2.numberOfTenants()).thenReturn(0);
        when(room3.numberOfTenants()).thenReturn(1);
    }


    @Test
    public void testAssignTenantAddsTenantToTheRoom() {
        //setup
        PersonId personId = PersonId.createFrom("person1");
        //act
        testObject.assignTenant(personId);
        //assert
        assertTrue(testObject.tenantIds().contains(personId));
    }

    @Test(expected = DomainException.class)
    public void testAssignTenantAssignTheSameTenantTwiceThrowException() {
        //setup
        PersonId personId = PersonId.createFrom("person1");
        //act
        testObject.assignTenant(personId);
        testObject.assignTenant(personId);
        //assert
        assertTrue(testObject.tenantIds().size() == 1);
    }

    @Test
    public void testUnassignTenantRemovesTenantFromRoom() {
        //setup
        PersonId personId = PersonId.createFrom("person1");
        testObject.assignTenant(personId);
        //act
        testObject.unassignTenant(personId);
        //assert
        assertFalse(testObject.tenantIds().contains(personId));
    }

    @Test(expected = DomainException.class)
    public void testUnassignTenantRemovesInvalidTenantThrowsException() {
        //act
        testObject.unassignTenant(personId1Mock);
    }

    @Test
    public void testTenantsIds() {
        //setup
        testObject.assignTenant(mock(PersonId.class));
        testObject.assignTenant(mock(PersonId.class));
        testObject.assignTenant(mock(PersonId.class));
        //act
        Set<PersonId> ids = testObject.tenantIds();
        //assert
        assertEquals(3, ids.size());
    }

    @Test
    public void testCreateRoomAddsTheNewRoom() {
        //act
        testObject.createRoom("", RoomType.SINGLE, 1);

        //assert
        assertThat(testObject.rooms(), Matchers.hasSize(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateRoomWithZeroRoomCapacityThrowsException() {
        //act
        testObject.createRoom(RoomType.SINGLE, 0);
    }

    @Test(expected = Exception.class)
    public void testCreateRoomWithNullCapacityThrowsException() {
        //act
        testObject.createRoom(RoomType.SINGLE, null);
    }

    @Test
    public void testAssignTenantToRoomAddsTenantToTheRoom() {
        //setup
        setupRoomSet();
        testObject.assignTenant(personId1Mock);

        //act
        testObject.assignTenantToRoom(personId1Mock, roomIdMock);

        //assert
        verify(room1).assign(personId1Mock);
    }

    @Test(expected = DomainException.class)
    public void testAssignTenantToRoomWhenRoomNotFoundThrowsException() {
        //setup
        Set<Room> roomSet = Sets.newHashSet(room1);
        RoomId roomIdMockDifferent = mock(RoomId.class);
        when(room1.getID()).thenReturn(roomIdMockDifferent);
        testObject.setRooms(roomSet);

        //act
        testObject.assignTenantToRoom(personId1Mock, roomIdMock);

        //assert
        verify(room1, never()).assign(personId1Mock);
    }

    @Test(expected = DomainException.class)
    public void testAssignTenantToRoomWhenTenantNotBelongsToThePropertyThrowsException() {
        //setup
        setupRoomSet();
        testObject.assignTenant(personId1Mock);

        //act
        testObject.assignTenantToRoom(personId2Mock, roomIdMock);

        //assert
        verify(room1, never()).assign(personId1Mock);
    }

    @Test
    public void testUnassignTenantFromRoomRemovesTenantFromRoom() {
        //setup
        setupRoomSet();
        testObject.assignTenant(personId1Mock);

        //act
        testObject.unassignTenantFromRoom(personId1Mock, roomIdMock);

        //assert
        verify(room1).unassign(personId1Mock);
    }

    @Test(expected = DomainException.class)
    public void testUnassignTenantFromRoomWhenTenantNotBelongsToThePropertyThrowsException() {
        //setup
        setupRoomSet();
        testObject.assignTenant(personId1Mock);

        //act
        testObject.unassignTenantFromRoom(personId2Mock, roomIdMock);

        //assert
        verify(room1).unassign(personId2Mock);
    }

    @Test
    public void testFindFreeRoomsReturnsTheFreeRoom() {
        //setup
        Set<Room> roomSet = Sets.newHashSet(room1, room2, room3);
        testObject.setRooms(roomSet);

        //act
        Set<Room> result = testObject.findFreeRooms();

        //assert
        assertThat(result, Matchers.contains(room2));
        assertThat(result, Matchers.hasSize(1));

    }


    private void setupRoomSet() {
        Set<Room> roomSet = Sets.newHashSet(room1);
        when(room1.getID()).thenReturn(roomIdMock);
        testObject.setRooms(roomSet);
    }

}