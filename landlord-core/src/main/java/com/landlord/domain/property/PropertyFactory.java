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

    public Property createProperty(String propertyName, SharingType sharingType, PropertyType propertyType) {
        String propertyId = idGenerator.generate();
        if (Strings.isNullOrEmpty(propertyName)) {
            propertyName = propertyId;
        }
        Property property = new Property(PropertyId.createFrom(propertyId), propertyName, sharingType, propertyType);
        property.setIdGenerator(idGenerator);
        return property;
    }
}
