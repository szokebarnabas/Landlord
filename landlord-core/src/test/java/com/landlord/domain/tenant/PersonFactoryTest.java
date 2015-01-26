package com.landlord.domain.tenant;

import com.landlord.domain.person.*;
import com.landlord.domain.person.Person;
import com.landlord.domain.Address;
import com.landlord.domain.person.PhoneNumber;
import landlord.ddd.pattern.idgeneration.IDGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class PersonFactoryTest {

    @Mock
    private IDGenerator generator;

    @InjectMocks
    private PersonFactory personFactory;

    @Test
    public void testCreateNewPerson() {
        //arrange
        //act
        Person person = personFactory.newTenant(Mockito.mock(PersonName.class), Mockito.mock(BirthDate.class), Mockito.mock(Address.class), Mockito.mock(PhoneNumber.class));

        //assert
        assertNotNull(person);
    }

    @Test
    public void testCreateNewPersonGeneratesId() {
        //arrange
        Mockito.when(generator.generate()).thenReturn("generated-id");

        //act
        Person person = personFactory.newOwner(Mockito.mock(PersonName.class), Mockito.mock(BirthDate.class), Mockito.mock(Address.class), Mockito.mock(PhoneNumber.class));

        //assert
        assertNotNull(person.getID());
        assertEquals("generated-id", person.getID().toString());
    }

    @Test(expected = Exception.class)
    public void testCreateNewPersonWithNullPersonNameThrowsException() {
        personFactory.newTenant(null, Mockito.mock(BirthDate.class), Mockito.mock(Address.class), Mockito.mock(PhoneNumber.class));
    }

    @Test(expected = Exception.class)
    public void testCreateNewPersonWithNullBirthDateThrowsException() {
        personFactory.newTenant(Mockito.mock(PersonName.class), null, Mockito.mock(Address.class), Mockito.mock(PhoneNumber.class));
    }

    @Test(expected = Exception.class)
    public void testCreateNewPersonWithNullAddressThrowsException() {
        personFactory.newTenant(Mockito.mock(PersonName.class), Mockito.mock(BirthDate.class), null, Mockito.mock(PhoneNumber.class));
    }

    @Test(expected = Exception.class)
    public void testCreateNewPersonWithNullPhoneNumberThrowsException() {
        personFactory.newTenant(Mockito.mock(PersonName.class), Mockito.mock(BirthDate.class), Mockito.mock(Address.class), null);
    }
}