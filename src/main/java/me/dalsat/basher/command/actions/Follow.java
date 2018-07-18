package me.dalsat.basher.command.actions;

import me.dalsat.basher.command.Command;
import me.dalsat.basher.store.core.Store;

/**
 * Provides the "follow" command, that allows a user to follow another user.
 */
public class Follow implements Action {

    @Override
    public String commandName() {
        return "follow";
    }

    @Override
    public String execute(Command command, Store store) {
        var follower = store.getOrAddUser(command.getUsername());
        var parameter = command.getParameter();
        if (parameter.isPresent()) {
            follower.follow(store.getOrAddUser(parameter.get()));
            return "follow -> ok";
        } else {
            return "specify the user to follow";
        }
    }
}
