package com.landlord.domain.utilities;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.landlord.domain.property.PropertyId;
import landlord.ddd.pattern.model.Entity;

import java.util.Collection;

class UtilityEntry extends Entity<UtilityEntryId> {

    private PropertyId propertyId;
    private Multimap<UtilityType, MeterValue> map;

    public UtilityEntry(UtilityEntryId id, PropertyId propertyId) {
        super(id);
        this.propertyId = propertyId;
        this.map = HashMultimap.create();
    }

    public void addMeterValue(UtilityType type, MeterValue meterValue) {
        map.put(type, meterValue);
    }

    @VisibleForTesting
    protected void setMap(Multimap<UtilityType, MeterValue> map) {
        this.map = map;
    }

    public Collection<MeterValue> meterValuesByType(UtilityType type) {
        return map.get(type);
    }
}
