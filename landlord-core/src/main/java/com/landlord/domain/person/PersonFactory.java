package com.landlord.domain.person;

import com.landlord.domain.Address;
import com.landlord.domain.PersonId;
import landlord.ddd.pattern.idgeneration.IDGenerator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class PersonFactory {

    @Inject
    private IDGenerator<String> idGenerator;

    private Person newPerson(PersonName personName, BirthDate birthDate, Address address, PhoneNumber phoneNumber, PersonRole role) {
        PersonId personId = PersonId.createFrom(idGenerator.generate());
        return new Person(personId, personName, birthDate, address, phoneNumber, role);
    }

    public Person newTenant(PersonName personName, BirthDate birthDate, Address address, PhoneNumber phoneNumber) {
        return newPerson(personName, birthDate, address, phoneNumber, PersonRole.TENANT);
    }

    public Person newOwner(PersonName personName, BirthDate birthDate, Address address, PhoneNumber phoneNumber) {
        return newPerson(personName, birthDate, address, phoneNumber, PersonRole.OWNER);
    }

    public Person newAgent(PersonName personName, BirthDate birthDate, Address address, PhoneNumber phoneNumber) {
        return newPerson(personName, birthDate, address, phoneNumber, PersonRole.AGENT);
    }
}
