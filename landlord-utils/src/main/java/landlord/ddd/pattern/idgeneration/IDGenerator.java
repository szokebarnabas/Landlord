package landlord.ddd.pattern.idgeneration;

public interface IDGenerator<T> {
    T generate();
}

