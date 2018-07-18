package me.dalsat.basher.command;

import java.util.Optional;

/**
 * Models a command issued from the command line, separated into its
 * components: the issuing user, the action, and the parameter.
 */
public class Command {
    private String username;
    private String action;
    private String parameter;

    private Command() {}

    public static Command forUser(String username) {
        var newCommand = new Command();
        newCommand.username = username;
        return newCommand;
    }

    public String getUsername() {
        return username;
    }

    public Optional<String> getAction() {
        return Optional.ofNullable(action);
    }

    public Optional<String> getParameter() {
        return Optional.ofNullable(parameter);
    }

    public void setAction(String action) {
        if (action.isEmpty()) {
            this.action= null;
        } else {
            this.action = action;
        }
    }

    public void setParameter(String parameter) {
        if (parameter.isEmpty()) {
            this.parameter = null;
        } else {
            this.parameter = parameter;
        }
    }

    @Override
    public String toString() {
        return String.format("Command[username='%s', action='%s', parameter='%s'",
                username, action, parameter);
    }
}
