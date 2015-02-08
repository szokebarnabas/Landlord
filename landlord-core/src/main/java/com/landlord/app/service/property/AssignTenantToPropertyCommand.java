package com.landlord.app.service.property;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class AssignTenantToPropertyCommand {
    private final String tenantId;
    private final String propertyId;
}
