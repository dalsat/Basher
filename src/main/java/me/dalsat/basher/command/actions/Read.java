package me.dalsat.basher.command.actions;

import me.dalsat.basher.command.Command;
import me.dalsat.basher.command.Message;
import me.dalsat.basher.store.core.Store;

import java.util.stream.Collectors;

/**
 * Provides the "read" command, that visualizes the messages published
 * by a user.
 */
public class Read implements Action {

    @Override
    public String commandName() { return ""; }

    @Override
    public String execute(Command command, Store store) {
        var user = store.getOrAddUser(command.getUsername());

        return user.listOfMessages().stream()
                .map(Message::toString)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public boolean canHandle(Command command) {
        return !command.getAction().isPresent();
    }

}
