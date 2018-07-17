package me.dalsat.basher.store;

import me.dalsat.basher.store.core.Store;
import me.dalsat.basher.store.mongo.MongoStore;
import me.dalsat.basher.store.mongo.MongoUser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MongoStoreTest extends StoreTest {

    @Override
    Store newStore() {
        return new MongoStore("basher-test");
    }

    @Test
    void testAddOrCreateUser() {
        super.testAddOrCreateUser();
        assertEquals(((MongoUser)ada).getId(), ((MongoUser)store.getOrAddUser("ada")).getId());

    }

    @Test
    void testListOfUsers() {
        var users = store.listOfUsers();
        assertEquals(2, users.size());
        assertTrue(users.contains(ada), "list of users does not contain ada");
        assertTrue(users.contains(bob), "list of users does not contain bob");
    }

    @Test
    void testPostMessage() {
        final var testMessage = "hello from ada";
        ada.postMessage(testMessage);

        store = newStore();
        final var messages = store.getOrAddUser("ada").listOfMessages();

        assertAll("post message",
                () -> assertEquals(1, messages.size()),
                () -> assertEquals(testMessage, messages.get(0).getText())
        );
    }

}
