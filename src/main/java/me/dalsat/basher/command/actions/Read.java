package me.dalsat.basher.command.actions;

import me.dalsat.basher.command.Command;
import me.dalsat.basher.store.core.Store;

import java.util.List;

public class Read implements Action {

    @Override
    public String commandName() { return ""; }

    @Override
    public void execute(Command command, Store store) {
        var user = store.getOrAddUser(command.getUsername());
        printMessages(store.messagesFor(user));
    }

    @Override
    public boolean canHandle(Command command) {
        return !command.getAction().isPresent();
    }

    private void printMessages(List<?> messages) {
        messages.forEach(System.out::println);
    }
}
