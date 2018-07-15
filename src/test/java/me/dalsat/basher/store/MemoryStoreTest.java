package me.dalsat.basher.store;

import me.dalsat.basher.store.core.Store;
import me.dalsat.basher.store.memory.MemoryStore;

public class MemoryStoreTest extends StoreTest {

    @Override
    Store newStore() {
        return new MemoryStore();
    }
}
