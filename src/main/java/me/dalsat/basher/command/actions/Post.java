package me.dalsat.basher.command.actions;

import me.dalsat.basher.command.Command;
import me.dalsat.basher.store.core.Store;

public class Post implements Action {

    @Override
    public String commandName() {
        return "->";
    }

    @Override
    public void execute(Command command, Store store) {
        var user = store.getOrAddUser(command.getUsername());
        command.getParameter().ifPresentOrElse(
                parameter -> user.postMessage(parameter),
                () -> System.err.println("empty message"));
    }

}
