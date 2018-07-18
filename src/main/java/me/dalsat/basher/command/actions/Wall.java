package me.dalsat.basher.command.actions;

import me.dalsat.basher.command.Command;
import me.dalsat.basher.command.Message;
import me.dalsat.basher.store.core.Store;

import java.util.stream.Collectors;

/**
 * Provides the "wall" command, that visualizes a list of the messages posted
 * by a user, plus the messages posted by the users that she follows.
 */
public class Wall implements Action {

    @Override
    public String commandName() {
        return "wall";
    }


    @Override
    public String execute(Command command, Store store) {
        var user = store.getOrAddUser(command.getUsername());

        return user.wall()
                .map(Message::toString)
                .collect(Collectors.joining("\n"));
    }
}
