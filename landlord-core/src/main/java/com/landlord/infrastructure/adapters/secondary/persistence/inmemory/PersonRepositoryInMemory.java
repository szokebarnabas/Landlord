package com.landlord.infrastructure.adapters.secondary.persistence.inmemory;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.landlord.domain.person.Person;
import com.landlord.domain.person.PersonRepository;
import com.landlord.domain.PersonId;

import javax.inject.Named;
import java.util.Map;
import java.util.Set;

@Named
public class PersonRepositoryInMemory implements PersonRepository {

    private Map<PersonId, Person> personMap = Maps.newHashMap();

    @Override
    public Person find(PersonId personId) {
        return personMap.get(personId);
    }

    @Override
    public Set<Person> findAll() {
        return Sets.newHashSet(personMap.values());
    }

    @Override
    public void create(Person person) {
        personMap.put(person.getID(), person);
    }

    @Override
    public void update(Person person) {
        personMap.put(person.getID(), person);
    }
}
