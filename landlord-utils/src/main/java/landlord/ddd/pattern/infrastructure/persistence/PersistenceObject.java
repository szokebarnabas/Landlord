package landlord.ddd.pattern.infrastructure.persistence;

import landlord.ddd.pattern.model.Domain;

public interface PersistenceObject<D extends Domain> {

    PersistenceObjectId id();
}
