package me.dalsat.basher.store;

import me.dalsat.basher.store.core.Store;
import me.dalsat.basher.store.memory.MemoryStore;
import me.dalsat.basher.store.mongo.MongoStore;

/**
 * Utility factory class to create the appropriate store according to
 * the settings.
 */
public interface Stores {

    /**
     * Creates the appropriate database specified in the environment variable BASHER_DB
     * @return
     */
    static Store getStore() {
        var dbType = System.getenv("BASHER_DB");
        if (dbType == null) {
            dbType = "mongo";
        }

        if (dbType.equals("mongo")) {
            System.out.println("Initializing MongoDB");
            return newMongoStore();
        }

        // default store: in-memory
        System.out.println("Initializing in-memory database");
        return newMemoryStore();
    }

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
