package com.landlord.domain.utilities;

import com.landlord.domain.Address;
import com.landlord.domain.Notes;
import landlord.ddd.pattern.model.Entity;

public class UtilityProvider extends Entity<ProviderId> {

    private String providerName;
    private Address address;
    private Notes notes;

    protected UtilityProvider(ProviderId id, String providerName, Address address) {
        super(id);
        this.providerName = providerName;
        this.address = address;
    }

    public void changeProviderName(String providerName) {
        this.providerName = providerName;
    }

    public void changeAddress(Address address) {
        this.address = address;
    }

    public String getProviderName() {
        return providerName;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    public Address getAddress() {
        return address;
    }
}
