package com.landlord.domain.utilities;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.landlord.domain.property.PropertyId;
import landlord.ddd.pattern.model.AggregateRoot;

import java.util.Collection;

public class Utilities extends AggregateRoot<UtilityDetailsId> {

    private Multimap<PropertyId, UtilityEntry> entryMap;

    public Utilities(UtilityDetailsId id) {
        super(id);
        this.entryMap = HashMultimap.create();
    }

    public void addNewUtilityEntry(PropertyId propertyId, UtilityEntry entry) {
        entryMap.put(propertyId, entry);
    }

    public Collection<UtilityEntry> findEntriesByPropertyId(PropertyId propertyId) {
        return entryMap.get(propertyId);
    }

    @VisibleForTesting
    protected void setEntryMap(Multimap<PropertyId, UtilityEntry> entryMap) {
        this.entryMap = entryMap;
    }
}
