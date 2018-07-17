package me.dalsat.basher.store.memory;

import me.dalsat.basher.command.Message;
import me.dalsat.basher.store.core.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MemoryUser implements User {
    private String name;

    private List<User> follows = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();

    public MemoryUser() {}

    public static MemoryUser named(String name) {
        var newUser = new MemoryUser();
        newUser.name = name;
        return newUser;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void follow(User user) {
        if (!follows.contains(user)) {
            follows.add(user);
        }
    }

    @Override
    public String toString() {
        return String.format("MemoryUser[username='%s']", name);
    }

    @Override
    public void postMessage(String message) {
        messages.add(Message.newMessage(this, message));
    }

    @Override
    public List<Message> listOfMessages() {
        return List.copyOf(messages);
    }

    @Override
    public Stream<Message> wall() {
        return follows.stream()
                .flatMap(eachUser -> eachUser.listOfMessages().stream())
                .sorted();
    }
}
