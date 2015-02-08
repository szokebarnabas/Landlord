package com.landlord.domain.property;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class PropertyServiceImpl implements PropertyService {

    private static final Logger LOG = LoggerFactory.getLogger(PropertyServiceImpl.class);
    @Inject
    private PropertyRepository propertyRepository;
    @Inject
    private PropertyFactory propertyFactory;

    public PropertyId createProperty(SharingType sharingType, PropertyType propertyType, Integer numberOfRooms) {
        Property property = propertyFactory.createProperty(sharingType, propertyType, numberOfRooms);
        propertyRepository.create(property);
        LOG.debug("Property has been created with ID: {}", property.getID());
        return property.getID();
    }
}
