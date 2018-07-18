package me.dalsat.basher.store.memory;

import me.dalsat.basher.store.core.Store;
import me.dalsat.basher.store.core.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * Implements the in-memory database, that stores messages and users without
 * the need for setting an external database, but that gets deleted after
 * the end of the execution of the application.
 */
public class MemoryStore implements Store {

    private Map<String, User> users = new HashMap<>();

    public MemoryStore() { reset(); }

    private Optional<User> getUser(String username) {
        return Optional.ofNullable(users.getOrDefault(username, null));
    }

    @Override
    public User getOrAddUser(String username) {
        return getUser(username).orElseGet(() -> addUser(username));
    }

    private User addUser(String username) {
        User newUser = newUser(username);
        users.put(username, newUser);
        newUser.follow(newUser);
        return newUser;
    }

    private User newUser(String username) {
        return MemoryUser.named(username);
    }

    @Override
    public Collection<? extends User> listOfUsers() {
        return users.values();
    }

    @Override
    public void reset() {
        users = new HashMap<>();
    }

}
