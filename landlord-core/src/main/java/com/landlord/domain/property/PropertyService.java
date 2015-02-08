package com.landlord.domain.property;

public interface PropertyService {

    PropertyId createProperty(SharingType sharingType, PropertyType propertyType, Integer numberOfRooms);
}
