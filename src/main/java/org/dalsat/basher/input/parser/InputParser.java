package org.dalsat.basher.input.parser;

import org.dalsat.basher.command.Command;

import java.util.Optional;


public interface InputParser {

    Optional<Command> parseCommand(String line);
}
