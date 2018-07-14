package org.dalsat.basher.store;

import org.dalsat.basher.store.core.Store;
import org.dalsat.basher.store.memory.MemoryStore;

public class MemoryStoreTest extends StoreTest {

    @Override
    Store newStore() {
        return new MemoryStore();
    }
}
