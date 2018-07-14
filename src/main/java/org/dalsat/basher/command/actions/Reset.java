package org.dalsat.basher.command.actions;

import org.dalsat.basher.command.Command;
import org.dalsat.basher.store.core.Store;

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
