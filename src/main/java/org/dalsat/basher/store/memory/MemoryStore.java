package org.dalsat.basher.store.memory;

import org.dalsat.basher.command.Message;
import org.dalsat.basher.store.core.User;
import org.dalsat.basher.store.core.Store;

import java.util.*;
import java.util.stream.Stream;

public class MemoryStore implements Store {

    private Map<String, User> users;
    long userCounter;

    private Map<User, List<Message>> messages;

    private Map<User, Set<User>> follows;

    private void initializeStore() {
        users = new HashMap<>();
        userCounter = 0;
        messages = new HashMap<>();
        follows = new HashMap<>();
    }

    public MemoryStore() { initializeStore(); }

    private Optional<User> getUser(String username) {
        return Optional.ofNullable(users.getOrDefault(username, null));
    }

    @Override
    public User getOrAddUser(String username) {
        return getUser(username).orElseGet(() -> addUser(username));
    }

    private User addUser(String username) {
        User newUser = new User(userCounter ++, username);
        users.put(username, newUser);
        messages.put(newUser, new ArrayList<>());
        follows.put(newUser, new HashSet<>());
        follow(newUser, newUser);
        return newUser;
    }

    @Override
    public void postMessage(Message message) {
        messages.get(message.getUser()).add(message);
    }

    @Override
    public List<Message> messagesFor(User user) {
        return Collections.unmodifiableList(messages.get(user));
    }

    @Override
    public Stream<Message> wall(User user) {
        return follows.get(user).stream()
                .flatMap(eachUser -> messagesFor(eachUser).stream())
                .sorted();
    }

    @Override
    public void follow(User follower, User followee) {
        follows.get(follower).add(followee);
    }

    @Override
    public void unfollow(User follower, User followee) {
        follows.get(follower).remove(followee);
    }

    @Override
    public Collection<User> listOfUsers() {
        return users.values();
    }

    @Override
    public void reset() {
        initializeStore();
    }

}
