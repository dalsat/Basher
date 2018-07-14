package org.dalsat.basher.command.actions;

import org.dalsat.basher.command.Command;
import org.dalsat.basher.store.core.Store;

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
