package me.dalsat.basher.command;

import me.dalsat.basher.command.actions.*;
import me.dalsat.basher.store.core.Store;

import java.util.List;
import java.util.Optional;

/**
 * Provides the functionality to select and perform the appropriate action from the
 * command that it receives.
 * 
 * To add a new action, insert into the <code>actions</code> list an
 * implementation of the interface <code>Action</code>.
 */
public class ActionExecutor {

    private List<Action> actions = List.of(
            new Read(),
            new Post(),
            new Follow(),
            new Wall(),
            new ListUsers(),
            new Reset(),
            new Quit()
    );

    private Optional<Action> actionFor(Command command) {
        return actions.stream()
                .filter(action -> action.canHandle(command))
                .findFirst();
    }

    public String execute(Command command, Store store) {
        var action = actionFor(command);
        if (action.isPresent()) {
            return action.get().execute(command, store);
        } else {
            return "invalid action";
        }
    }
}
