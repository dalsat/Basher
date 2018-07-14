package org.dalsat.basher.command;

import org.dalsat.basher.store.core.User;

import java.time.Duration;
import java.time.Instant;

public class Message implements Comparable<Message> {

    private User user;
    private String text;
    public final Instant TIMESTAMP = Instant.now();

    private Message() {};

    public static Message newMessage(User user, String text) {
        Message newMessage = new Message();
        newMessage.user = user;
        newMessage.text = text;
        return newMessage;
    }

    public User getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public Duration timeDelta() {
        return Duration.between(TIMESTAMP, Instant.now());
    }

    private String deltaString() {
        var deltaString = new StringBuilder();
        var seconds = timeDelta().getSeconds();

        if (seconds / 60 > 0) {
            deltaString.append(seconds / 60);
            deltaString.append("m");
        } else {
            deltaString.append(seconds);
            deltaString.append("s");
        }
        return deltaString.toString();
    }

    @Override
    public String toString() {
        return '[' + user.getName() + "] " + text + " - " + deltaString();
    }

    @Override
    public int compareTo(Message message) {
        if (TIMESTAMP.isBefore(message.TIMESTAMP)) {
            return -1;
        } else {
            return 1;
        }
    }
}
