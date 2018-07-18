package me.dalsat.basher.command;

import me.dalsat.basher.store.core.User;

import java.time.Duration;
import java.time.Instant;

/**
 * Models a message (post) published by a user.
 * It contains the author, the time of publishing, and the contents of
 * the message.
 */
public class Message implements Comparable<Message> {

    protected User user;
    protected String text;
    public final Instant TIMESTAMP = Instant.now();

    protected Message() {}

    public static Message newMessage(User user, String text) {
        Message newMessage = new Message();
        newMessage.user = user;
        newMessage.text = text;
        return newMessage;
    }

    public void setUser(User user) { this.user = user; }

    public User getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    private Duration timeDelta() {
        return Duration.between(TIMESTAMP, Instant.now());
    }

    private String deltaPrintString() {
        var deltaString = new StringBuilder();
        var seconds = timeDelta().getSeconds();

        long amount = seconds;
        if (seconds / 60 > 0) {
            amount = seconds / 60;
            deltaString.append(amount);
            deltaString.append(" minute");
        } else {
            deltaString.append(amount);
            deltaString.append(" second");
        }
        if (amount != 1) {
            deltaString.append('s');
        }
        deltaString.append(" ago");
        return deltaString.toString();
    }

    @Override
    public String toString() {
        return user.getName() + " - " + text + " (" + deltaPrintString() + ')';
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
