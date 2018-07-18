package me.dalsat.basher.command.actions;

import me.dalsat.basher.command.Command;
import me.dalsat.basher.store.core.Store;

/**
 * Provides the "reset" command, that clears the current database.
 */
public class Reset implements Action {

    @Override
    public String commandName() {
        return "reset";
    }


    @Override
    public String execute(Command command, Store store) {
        store.reset();
        return "reset -> database cleared";
    }
}
