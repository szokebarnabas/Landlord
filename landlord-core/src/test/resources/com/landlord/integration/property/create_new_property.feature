Feature: Create new property

  Scenario: I create a new property and assign tenants to rooms

    Given I create a 'SHARED' property 'PROPERTY_1' with the following rooms
    | roomName | roomType | numberOfBeds |
    | ROOM_1   | SINGLE   | 1            |
    | ROOM_2   | DOUBLE   | 2            |
    | ROOM_3   | ENSUIT   | 2            |

    And I add tenant 'TENANT_1' to 'PROPERTY_1'
    And I add tenant 'TENANT_3' to 'PROPERTY_1'
    And I add tenant 'TENANT_2' to 'PROPERTY_1'

    When I assign 'TENANT_1' to room 'ROOM_1' in 'PROPERTY_1'
    When I assign 'TENANT_2' to room 'ROOM_2' in 'PROPERTY_1'
    When I assign 'TENANT_3' to room 'ROOM_2' in 'PROPERTY_1'

    Then 'TENANT_1' is assigned to room 'ROOM_1' in 'PROPERTY_1'
    Then 'TENANT_3' is assigned to room 'ROOM_2' in 'PROPERTY_1'
    Then 'TENANT_2' is assigned to room 'ROOM_2' in 'PROPERTY_1'

    Then The following rooms are free: 'ROOM_3' in 'PROPERTY_1'