package me.dalsat.basher.command.actions;

import me.dalsat.basher.command.Command;
import me.dalsat.basher.store.core.Store;


/**
 * Describes a command line action that the user can perform.
 * It defines the command name, the condition to activate, and the action to
 * perform when activated.
 */
public interface Action {

    /**
     * Return the name of the command that the user can type.
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
     * @return a message describing the result of the action
     */
    String execute(Command command, Store store);

}
