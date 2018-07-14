package org.dalsat.basher.command.actions;

import org.dalsat.basher.command.Command;
import org.dalsat.basher.manager.Basher;
import org.dalsat.basher.store.core.Store;

public class Quit implements Action {

    @Override
    public String commandName() { return "quit"; }


    @Override
    public void execute(Command command, Store store) {
        System.out.println("quitting...");
        Basher.stop();
    }

}
