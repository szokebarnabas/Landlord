package com.landlord.infrastructure.adapters.secondary.persistence.inmemory;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.landlord.domain.property.Property;
import com.landlord.domain.property.PropertyId;
import com.landlord.domain.property.PropertyRepository;

import javax.inject.Named;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Named
public class PropertyRepositoryInMemory implements PropertyRepository {

    private final Map<PropertyId, Property> propertyMap = Maps.newHashMap();
    private final ConcurrentMap<PropertyId, Lock> locks = Maps.newConcurrentMap();

    @Override
    public Property find(PropertyId propertyId) {
        return propertyMap.get(propertyId);
    }

    @Override
    public Set<Property> findAll() {
        return Sets.newHashSet(propertyMap.values());
    }

    @Override
    public void create(Property property) {
        propertyMap.put(property.getID(), property);
    }

    @Override
    public void update(Property property) {
        propertyMap.put(property.getID(), property);
    }

    @Override
    public void clear() {
        propertyMap.clear();
    }

    @Override
    public Lock getLockForKey(PropertyId key) {
        locks.putIfAbsent(key, new ReentrantLock());
        return locks.get(key);
    }
}
