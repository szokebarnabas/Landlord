package landlord.ddd.pattern.model;

public class AggregateRoot<T extends ID> extends Entity<T>{

    protected AggregateRoot(T id) {
        super(id);
    }
}
