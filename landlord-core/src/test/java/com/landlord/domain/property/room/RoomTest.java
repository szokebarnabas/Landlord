package com.landlord.domain.property.room;

import com.landlord.domain.Notes;
import com.landlord.domain.PersonId;
import landlord.ddd.pattern.model.DomainException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Set;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoomTest {

    public static final int ROOM_CAPACITY = 2;

    private Room testObject;

    @Mock
    private Set<PersonId> tenantSet;

    @Before
    public void init() {
        testObject = Room.with(mock(RoomId.class), "room1", RoomType.DOUBLE, ROOM_CAPACITY, tenantSet);
    }

    @Test
    public void testAssignAddsANewTenantId() {
        //setup
        PersonId personId = mock(PersonId.class);

        //act
        testObject.assign(personId);

        //assert
        verify(tenantSet).add(personId);
    }

    @Test(expected = DomainException.class)
    public void testAssignAddingExistingPersonIdThrowsException() {
        //setup
        PersonId personId = mock(PersonId.class);
        when(tenantSet.contains(personId)).thenReturn(true);

        //act
        testObject.assign(personId);
    }

    @Test(expected = DomainException.class)
    public void testAssignAddTenantWhenRoomIsFullThrowsException() {
        //setup
        PersonId personId = mock(PersonId.class);
        when(tenantSet.size()).thenReturn(2);

        //act
        testObject.assign(personId);
    }


    @Test
    public void testUnassignRemovesTenantFromTheRoom() {
        //setup
        PersonId personId = mock(PersonId.class);
        when(tenantSet.contains(personId)).thenReturn(true);

        //act
        testObject.unassign(personId);

        //assert
        verify(tenantSet).remove(personId);
    }

    @Test(expected = DomainException.class)
    public void testUnassignWhenTenantIsNotInTheRoomThrowsException() {
        //setup
        PersonId personId = mock(PersonId.class);
        when(tenantSet.contains(personId)).thenReturn(false);

        //act
        testObject.unassign(personId);
    }

    @Test
    public void testModifyNotesChangesTheNotesOfTheRoom() {
        //setup
        Notes notes = mock(Notes.class);

        //act
        testObject.modifyNotes(notes);

        //assert
        assertThat(testObject.notes(), equalTo(notes));
    }

    @Test
    public void testNotesReturnsTheNotes() {
        //setup
        Notes newNotes = mock(Notes.class);
        testObject.modifyNotes(newNotes);

        //act
        Notes notes = testObject.notes();

        //assert
        assertThat(notes, equalTo(newNotes));
    }

    @Test
    public void testTenantsReturnsTenantIds() {
        //setup
        PersonId personId = mock(PersonId.class);
        testObject.assign(personId);

        //act
        Set<PersonId> tenantSet = testObject.tenants();

        //assert
        assertThat(tenantSet, equalTo(tenantSet));
    }
}