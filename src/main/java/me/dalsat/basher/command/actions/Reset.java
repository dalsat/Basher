package me.dalsat.basher.command.actions;

import me.dalsat.basher.command.Command;
import me.dalsat.basher.store.core.Store;

public class Reset implements Action {

    @Override
    public String commandName() {
        return "reset";
    }


    @Override
    public void execute(Command command, Store store) {
        store.reset();
    }
}
