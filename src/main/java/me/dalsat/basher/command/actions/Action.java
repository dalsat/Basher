package me.dalsat.basher.command.actions;

import me.dalsat.basher.command.Command;
import me.dalsat.basher.store.core.Store;


public interface Action {

    /**
     * Return the name of the action represented by this object.
     * @return the name of the action
     */
    String commandName();

    /**
     * Determines whether this action is able to handle the current command
     * @param command the command to interpret
     * @return true if the action can handle the command, false otherwise
     */
    default boolean canHandle(Command command) {
        return command.getAction().isPresent()
                && command.getAction().get().equals(commandName());
    }

    /**
     * Performs the operation related to the issued command.
     * @param command the command to execute
     * @param store the store that contains the data about users and messages
     */
    void execute(Command command, Store store);

}
