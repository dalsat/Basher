package me.dalsat.basher.store.core;

import java.util.Collection;

public interface Store {

    /**
     * Returns a user given the username. If the user does not exist, create a new one.
     * @param username the name of the user
     * @return the object corresponding to the username
     */
    User getOrAddUser(String username);

    /**
     * Returns the list of users subscribed to the chat.
     * @return the list of existing users
     */
    Collection<? extends User> listOfUsers();

    /**
     * Removes all the data in the store.
     */
    void reset();
}
