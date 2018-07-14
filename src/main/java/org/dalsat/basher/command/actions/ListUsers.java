package org.dalsat.basher.command.actions;

import org.dalsat.basher.command.Command;
import org.dalsat.basher.store.core.Store;

public class ListUsers implements Action {
    @Override
    public String commandName() { return "users"; }

    @Override
    public void execute(Command command, Store store) {
        for (var user: store.listOfUsers()) {
            System.out.println(user.getName());
        }
    }
}
