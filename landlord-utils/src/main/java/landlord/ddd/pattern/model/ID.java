package landlord.ddd.pattern.model;

public abstract class ID extends ValueObject {

    private String value;

    protected ID(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
