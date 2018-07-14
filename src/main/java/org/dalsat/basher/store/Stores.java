package org.dalsat.basher.store;

import org.dalsat.basher.store.core.Store;
import org.dalsat.basher.store.memory.MemoryStore;
import org.dalsat.basher.store.mongo.MongoStore;

public interface Stores {

    /**
     * Creates a new in-memory database.
     * @return a new MemoryStore object
     */
    static Store newMemoryStore() {
        return new MemoryStore();
    }

    /**
     * Creates a new Mongo database.
     * @return a new MongoDBStore object
     */
    static Store newMongoStore() {
        return new MongoStore();
    }
}
