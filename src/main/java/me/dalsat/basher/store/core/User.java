package me.dalsat.basher.store.core;

import me.dalsat.basher.command.Message;

import java.util.List;
import java.util.stream.Stream;

public interface User {

    String getName();

    /**
     * Creates a 'follow' relation between this user and the followee.
     * @param user the user who is followed
     */
    void follow(User user);

    /**
     * Add a new message to the list of posted messages.
     * @param message a string with new message to post
     */
    void postMessage(String message);

    /**
     * Returns all the messages posted by a user.
     * @return the messages posted by the user
     */
    List<Message> listOfMessages();

    /**
     * Get the list of all the messages posted by the author and by the users she follows.
     * The messages are sorted by publication date.
     * @return a Stream of Messages
     */
    Stream<Message> wall();
}
