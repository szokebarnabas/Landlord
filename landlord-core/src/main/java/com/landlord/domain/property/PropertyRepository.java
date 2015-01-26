package com.landlord.domain.property;

import java.util.Set;
import java.util.concurrent.locks.Lock;

public interface PropertyRepository {

    Property find(PropertyId propertyId);

    Set<Property> findAll();

    void create(Property property);

    void update(Property property);

    void clear();

    Lock getLockForKey(PropertyId key);
}
