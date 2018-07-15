package me.dalsat.basher.command.actions;

import me.dalsat.basher.command.Command;
import me.dalsat.basher.store.core.Store;

public class Follow implements Action {

    @Override
    public String commandName() {
        return "follow";
    }

    @Override
    public void execute(Command command, Store store) {
        var follower = store.getOrAddUser(command.getUsername());
        command.getParameter().ifPresentOrElse(
                followee -> store.follow(follower, store.getOrAddUser(followee)),
                () -> System.err.println("specify the user to follow"));
    }
}
