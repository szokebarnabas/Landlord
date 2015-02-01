package com.landlord.domain.property;

import com.google.common.base.Strings;
import landlord.ddd.pattern.idgeneration.IDGenerator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class PropertyFactory {

    private IDGenerator<String> idGenerator;

    @Inject
    public void setIdGenerator(IDGenerator<String> idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Property createProperty(String propertyName, SharingType sharingType, PropertyType propertyType, Integer numberOfRooms) {
        String propertyId = idGenerator.generate();
        if (Strings.isNullOrEmpty(propertyName)) {
            propertyName = propertyId;
        }
        Property property = new Property(PropertyId.createFrom(propertyId), propertyName, sharingType, propertyType, numberOfRooms);
        property.setIdGenerator(idGenerator);
        return property;
    }

    public Property createProperty(SharingType sharingType, PropertyType propertyType, Integer numberOfRooms) {
        return createProperty(null, sharingType, propertyType, numberOfRooms);
    }
}
