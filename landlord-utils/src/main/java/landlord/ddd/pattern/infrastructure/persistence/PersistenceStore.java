package landlord.ddd.pattern.infrastructure.persistence;

import landlord.ddd.pattern.model.Domain;

public interface PersistenceStore<D extends Domain, P extends PersistenceObject<D>, PI extends PersistenceObjectId> {

    P find(PI id);
    void update(P po);
    void create(P po);
    void clear();
}
