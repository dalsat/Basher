package org.dalsat.basher.input;

import java.io.IOException;

public class InvalidCommandException extends IOException {

    String command;

    public InvalidCommandException(String command) {
        this.command = command;
    }
}
