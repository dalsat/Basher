package me.dalsat.basher.command.actions;

import me.dalsat.basher.command.Command;
import me.dalsat.basher.store.core.Store;

public class Read implements Action {

    @Override
    public String commandName() { return ""; }

    @Override
    public void execute(Command command, Store store) {
        var user = store.getOrAddUser(command.getUsername());
        user.listOfMessages().forEach(System.out::println);
    }

    @Override
    public boolean canHandle(Command command) {
        return !command.getAction().isPresent();
    }

}
