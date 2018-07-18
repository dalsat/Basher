package me.dalsat.basher.command.actions;

import me.dalsat.basher.command.Command;
import me.dalsat.basher.Basher;
import me.dalsat.basher.store.core.Store;

/**
 * Provides the "quit" command, that terminates the application.
 */
public class Quit implements Action {

    @Override
    public String commandName() { return "quit"; }


    @Override
    public String execute(Command command, Store store) {
        Basher.stop();
        return "";
    }

}
