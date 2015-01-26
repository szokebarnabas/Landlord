package com.landlord.util.locking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;

public class LockTemplate {

    private static final Logger LOG = LoggerFactory.getLogger(LockTemplate.class);

    public <K, T> T doWithLock(FinegrainedLockable<K> lockable, K key, WriteLockingAction<K, T> action) {
        Lock lock = lockable.getLockForKey(key);
        lock.lock();
        try {
            log(String.format("locked on %s using key %s", lockable, key));
            return action.withWriteLock(key);
        } finally {
            lock.unlock();
            log(String.format("unlocked on %s using key %s", lockable, key));
        }
    }

    private static void log(String logMessage) {
        if(LOG.isDebugEnabled()) {
            LOG.debug(logMessage);
        }
    }
}
