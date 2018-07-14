package org.dalsat.basher.command.actions;

import org.dalsat.basher.command.Command;
import org.dalsat.basher.command.Message;
import org.dalsat.basher.store.core.User;
import org.dalsat.basher.store.core.Store;

public class Post implements Action {

    @Override
    public String commandName() {
        return "->";
    }

    @Override
    public void execute(Command command, Store store) {
        var user = store.getOrAddUser(command.getUsername());
        command.getParameter().ifPresentOrElse(
                parameter -> postMessage(user, parameter, store),
                () -> System.err.println("empty message"));
    }

    private void postMessage(User user, String parameter, Store store) {
        var message = Message.newMessage(user, parameter);
        store.postMessage(message);
        System.out.println(message);
    }
}
