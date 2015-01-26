package com.landlord.domain.person;

import com.google.common.base.Preconditions;
import com.landlord.domain.Address;
import com.landlord.domain.PersonId;
import landlord.ddd.pattern.model.AggregateRoot;

public class Person extends AggregateRoot<PersonId> {

    private Salutation salutation;
    private PersonName name;
    private BirthDate birthDate;
    private Address address;
    private PhoneNumber phoneNumber;
    private PersonRole role;
    private Email email;

    public Person(PersonId id, PersonName name, BirthDate birthDate, Address address, PhoneNumber phoneNumber, PersonRole role) {
        super(id);
        Preconditions.checkNotNull(id);
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(birthDate);
        Preconditions.checkNotNull(address);
        Preconditions.checkNotNull(phoneNumber);
        Preconditions.checkNotNull(role);
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public PersonName getName() {
        return name;
    }

    public BirthDate getBirthDate() {
        return birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public PersonRole getRole() {
        return role;
    }

    public void changeAddress(Address address) {
        this.address = address;
    }

    public void changePhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void changeBirthDate(BirthDate birthDate) {
        this.birthDate = birthDate;
    }

    public void changeName(PersonName name) {
        this.name = name;
    }

    public void changeRole(PersonRole role) {
        this.role = role;
    }
}
