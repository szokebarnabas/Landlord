package com.landlord.util.locking;

public interface WriteLockingAction<K, T> {

    T withWriteLock(K key);

}
