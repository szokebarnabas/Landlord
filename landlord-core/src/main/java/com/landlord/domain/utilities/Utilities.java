package com.landlord.domain.utilities;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.landlord.domain.property.PropertyId;
import landlord.ddd.pattern.model.AggregateRoot;

public class Utilities extends AggregateRoot<UtilityDetailsId> {

    private Multimap<PropertyId, UtilityEntry> entryMap;

    public Utilities(UtilityDetailsId id) {
        super(id);
        this.entryMap = HashMultimap.create();
    }

    public void addNewUtilityEntry(PropertyId propertyId, UtilityEntry entry) {
        entryMap.put(propertyId, entry);
    }
}
