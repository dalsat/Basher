package org.dalsat.basher.store;

import org.dalsat.basher.command.Message;
import org.dalsat.basher.store.core.User;
import org.dalsat.basher.store.core.Store;
import org.dalsat.basher.store.memory.MemoryStore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MemoryStoreTest {

    Store store;
    User ada, bob;

    @BeforeAll
    static void setUpAll() {

    }

    @BeforeEach
    void setUp() {
        store = new MemoryStore();
        ada = store.getOrAddUser("ada");
        bob = store.getOrAddUser("bob");
    }

    @Test
    void testAddOrCreateUser() {
        assertEquals("ada", ada.getName());
    }

    @Test
    void testPostMessage() {
        var messageTest = "hello from ada";
        var message = Message.newMessage(ada, messageTest);
        store.postMessage(message);
    }

    @Test
    void testMessagesFor() {
        var adaMessage = "hello from ada";
        var bobMessage = "goodbye from bob";

        store.postMessage(Message.newMessage(ada, adaMessage));
        store.postMessage(Message.newMessage(bob, bobMessage));

        var retrievedAdaMessages = store.messagesFor(ada);
        var retrievedBobMessages = store.messagesFor(bob);

        assertAll ("number of messages",
                () -> assertEquals(1, retrievedAdaMessages.size()),
                () -> assertEquals(1, retrievedBobMessages.size())
        );

        assertAll ("message content",
                () -> assertEquals(adaMessage, retrievedAdaMessages.get(0).getText()),
                () -> assertEquals(bobMessage, retrievedBobMessages.get(0).getText())
        );

    }

    @Test
    void testWall() {
        var adaMessage = "hello from ada";
        var bobMessage = "goodbye from bob";

        store.postMessage(Message.newMessage(ada, adaMessage));
        store.postMessage(Message.newMessage(bob, bobMessage));

        store.follow(ada, bob);

        assertEquals(2, store.wall(ada).toArray().length);
    }

    @Test
    void testFollow() {}

    @Test
    void testListOfUsers() {
        var users = store.listOfUsers();
        assertEquals(2, users.size());
        assertTrue(users.contains(ada));
        assertTrue(users.contains(bob));
    }

}
