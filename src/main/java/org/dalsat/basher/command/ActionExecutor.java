package org.dalsat.basher.command;

import org.dalsat.basher.command.actions.*;
import org.dalsat.basher.store.core.Store;

import java.util.List;
import java.util.Optional;

public class ActionExecutor {

    List<Action> actions = List.of(
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

    public void execute(Command command, Store store) {
        actionFor(command)
            .ifPresentOrElse(
                    action -> action.execute(command, store),
                    () -> System.err.println("invalid action"));
    }
}
