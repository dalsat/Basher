package me.dalsat.basher.command.actions;

import me.dalsat.basher.command.Command;
import me.dalsat.basher.store.core.Store;
import me.dalsat.basher.store.core.User;

import java.util.stream.Collectors;

/**
 * Provides the "users" command, that prints the list of existing users
 * in the system.
 */
public class ListUsers implements Action {
    @Override
    public String commandName() { return "users"; }

    @Override
    public String execute(Command command, Store store) {
        StringBuilder sb = new StringBuilder();

        return store.listOfUsers().stream()
                .map(User::getName)
                .collect(Collectors.joining("\n"));
    }
}
