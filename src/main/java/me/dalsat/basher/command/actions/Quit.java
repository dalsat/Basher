package me.dalsat.basher.command.actions;

import me.dalsat.basher.command.Command;
import me.dalsat.basher.manager.Basher;
import me.dalsat.basher.store.core.Store;

public class Quit implements Action {

    @Override
    public String commandName() { return "quit"; }


    @Override
    public void execute(Command command, Store store) {
        System.out.println("quitting...");
        Basher.stop();
    }

}
