package me.dalsat.basher.command.actions;

import me.dalsat.basher.command.Command;
import me.dalsat.basher.store.core.Store;

public class Wall implements Action {

    @Override
    public String commandName() {
        return "wall";
    }


    @Override
    public void execute(Command command, Store store) {
        var user = store.getOrAddUser(command.getUsername());
        user.wall().forEach(System.out::println);
    }
}
