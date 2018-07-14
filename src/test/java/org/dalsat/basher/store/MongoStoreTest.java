package org.dalsat.basher.store;

import org.dalsat.basher.store.core.Store;
import org.dalsat.basher.store.mongo.MongoStore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


@Disabled
public class MongoStoreTest extends StoreTest {

    @Override
    Store newStore() {
//        return new MongoStore();
        return null;
    }

}
