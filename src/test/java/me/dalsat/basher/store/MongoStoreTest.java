package me.dalsat.basher.store;

import me.dalsat.basher.store.core.Store;
import org.junit.jupiter.api.Disabled;


@Disabled
public class MongoStoreTest extends StoreTest {

    @Override
    Store newStore() {
//        return new MongoStore();
        return null;
    }

}
