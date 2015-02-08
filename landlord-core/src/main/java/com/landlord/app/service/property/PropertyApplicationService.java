package com.landlord.app.service.property;

public interface PropertyApplicationService {

    void createProperty(CreatePropertyCommand createPropertyCommand);

    void createRoom(CreateRoomCommand createRoomCommand);

    void assignTenantToRoom(AssignTenantToRoomCommand assignTenantToRoomCommand);

    void assignTenantToProperty(AssignTenantToPropertyCommand assignTenantToPropertyCommand);

    void unassingTenantFromProperty(UnassignTenantFromPropertyCommand unassignTenantFromPropertyCommand);
}
