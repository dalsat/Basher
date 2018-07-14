package org.dalsat.basher.command.actions;

import org.dalsat.basher.command.Command;
import org.dalsat.basher.store.core.Store;

public class Wall implements Action {

    @Override
    public String commandName() {
        return "wall";
    }


    @Override
    public void execute(Command command, Store store) {
        var user = store.getOrAddUser(command.getUsername());
        store.wall(user).forEach(System.out::println);
    }
}
