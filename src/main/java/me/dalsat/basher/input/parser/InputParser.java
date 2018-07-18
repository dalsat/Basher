package me.dalsat.basher.input.parser;

import me.dalsat.basher.command.Command;

import java.util.Optional;


/**
 * Describes an input parser that can be plugged to the console to
 * parse commands.
 */
public interface InputParser {

    Optional<Command> parseCommand(String line);
}
