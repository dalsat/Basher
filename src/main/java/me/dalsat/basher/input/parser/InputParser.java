package me.dalsat.basher.input.parser;

import me.dalsat.basher.command.Command;

import java.util.Optional;


public interface InputParser {

    Optional<Command> parseCommand(String line);
}
