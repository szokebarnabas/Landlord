package com.landlord.util.locking;

public interface VoidWriteLockingAction<K> {
    void withWriteLock(K key);
}
