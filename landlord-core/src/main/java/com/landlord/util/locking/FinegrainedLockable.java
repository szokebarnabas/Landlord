package com.landlord.util.locking;

import java.util.concurrent.locks.Lock;

public interface FinegrainedLockable<T> {

    Lock getLockForKey(T key);

}
