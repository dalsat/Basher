package me.dalsat.basher.store;

import me.dalsat.basher.store.core.Store;
import me.dalsat.basher.store.core.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class StoreTest {

    protected Store store;
    protected User ada, bob;

    @BeforeAll
    static void setUpAll() {

    }

    @BeforeEach
    void setUp() {
        store = newStore();
        store.reset();
        ada = store.getOrAddUser("ada");
        bob = store.getOrAddUser("bob");
    }

    abstract Store newStore();

    @Test
    void testAddOrCreateUser() {
        assertEquals("ada", ada.getName());
    }

    @Test
    void testPostMessage() {
        var messageTest = "hello from ada";
        ada.postMessage(messageTest);
    }

    @Test
    void testMessagesFor() {
        var adaMessage = "hello from ada";
        var bobMessage = "goodbye from bob";

        ada.postMessage(adaMessage);
        bob.postMessage(bobMessage);

        var retrievedAdaMessages = ada.listOfMessages();
        var retrievedBobMessages = bob.listOfMessages();

        assertAll ("number of messages",
                () -> assertEquals(1, retrievedAdaMessages.size()),
                () -> assertEquals(1, retrievedBobMessages.size())
        );

        assertAll ("message content",
                () -> Assertions.assertEquals(adaMessage, retrievedAdaMessages.get(0).getText()),
                () -> Assertions.assertEquals(bobMessage, retrievedBobMessages.get(0).getText())
        );

    }

    @Test
    void testWall() {
        var adaMessage = "hello from ada";
        var bobMessage = "goodbye from bob";

        ada.postMessage(adaMessage);
        bob.postMessage(bobMessage);

        ada.follow(bob);

        assertEquals(2, ada.wall().toArray().length);
    }

    @Test
    void testFollow() {}

    @Test
    void testListOfUsers() {
        var users = store.listOfUsers();
        assertEquals(2, users.size());
        assertTrue(users.contains(ada), "list of users does not contain ada");
        assertTrue(users.contains(bob), "list of users does not contain bob");
    }

}
