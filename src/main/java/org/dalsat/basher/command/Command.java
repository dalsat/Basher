package org.dalsat.basher.command;

import java.util.Optional;

public class Command {
    String username;
    String action;
    String parameter;

    private Command() {}

    public Command(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Optional<String> getAction() {
        return Optional.ofNullable(action);
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public Optional<String> getParameter() {
        return Optional.ofNullable(parameter);
    }

    @Override
    public String toString() {
        String returnString = '[' + username + "] " + action;
        if (parameter != null) {
            returnString += " (" + parameter + ')';
        }
        return returnString;
    }
}
