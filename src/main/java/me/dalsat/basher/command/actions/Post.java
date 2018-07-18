package me.dalsat.basher.command.actions;

import me.dalsat.basher.command.Command;
import me.dalsat.basher.store.core.Store;

/**
 * Provides the "post" command, that allows a user to publish a new message.
 */
public class Post implements Action {

    @Override
    public String commandName() {
        return "->";
    }

    @Override
    public String execute(Command command, Store store) {
        var user = store.getOrAddUser(command.getUsername());
        var parameter = command.getParameter();
        if (parameter.isPresent()) {
            user.postMessage(parameter.get());
            return "post -> ok";
        } else {
            return "empty message";
        }
    }

}
