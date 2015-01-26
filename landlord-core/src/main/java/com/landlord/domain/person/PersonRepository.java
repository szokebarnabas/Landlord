package com.landlord.domain.person;

import com.landlord.domain.PersonId;
import landlord.ddd.pattern.model.Repository;

import java.util.Set;

public interface PersonRepository extends Repository<PersonId, Person> {

    Person find(PersonId personId);

    Set<Person> findAll();

    void create(Person person);

    void update(Person person);
}
