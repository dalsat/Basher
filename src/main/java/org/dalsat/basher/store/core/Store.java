package org.dalsat.basher.store.core;

import org.dalsat.basher.command.Message;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public interface Store {

    /**
     * Returns a user given the username. If the user does not exist, create a new one.
     * @param username
     * @return
     */
    User getOrAddUser(String username);

//    User addUser(String username);

    /**
     * Add a new message to the list of posted messages.
     * @param message the new message to be posted
     */
    void postMessage(Message message);

    /**
     * Returns all the messages posted by a user.
     * @param user the User object of the author of the posts.
     * @return the messages posted by a user
     */
    List<Message> messagesFor(User user);

    /**
     * Get the list of all the messages posted by the author and by the users she follows.
     * The messages are sorted by publication date.
     * @param user
     * @return
     */
    Stream<Message> wall(User user);

    /**
     * Create a 'follow' relation between the follower user and the followee.
     * @param follower the user that follows
     * @param followee the user who is followed
     */
    void follow(User follower, User followee);

    /**
     * Removes an existing 'follow' relation.
     * @param follower the user that follows
     * @param followee the user who is followed
     */
    void unfollow(User follower, User followee);

    /**
     * Returns the list of users subscribed to the chat.
     * @return A list of users
     */
    Collection<User> listOfUsers();

    /**
     * Removes all the data in the store.
     */
    void reset();
}
