package me.dalsat.basher.input;

import me.dalsat.basher.input.parser.InputParser;
import me.dalsat.basher.input.parser.RegexInputParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {

    private InputParser parser;

    private InputParser initializeParser() {
        return new RegexInputParser();
    }

    InputParserTest() {
        parser = initializeParser();
    }

    private void evaluateCommand(String tag, String input, String username,
                                 String action, String parameter) {
        parser.parseCommand(input).ifPresentOrElse(
                command -> assertAll(tag,
                        () -> assertEquals(username, command.getUsername()),
                        () -> assertEquals(Optional.ofNullable(action), command.getAction()),
                        () -> assertEquals(Optional.ofNullable(parameter), command.getParameter())
                ),
                Assertions::fail
        );
    }

    @Test
    void testParseEmpty() {
        assertEquals(Optional.empty(), parser.parseCommand(""));
    }

    @Test
    void testParseRead() {
        evaluateCommand("read",
                "Alice",
                "Alice",
                null,
                null);
    }

    @Test
    void testParsePost() {
        evaluateCommand("post",
                "Alice -> I love the weather today",
                "Alice",
                "->",
                "I love the weather today");
    }

    @Test
    void testParseWall() {
        evaluateCommand("wall",
                "Alice wall",
                "Alice",
                "wall",
                null);
    }

    @Test
    void testParseFollow() {
        evaluateCommand("follow",
                "Alice follow Bob",
                "Alice",
                "follow",
                "Bob");
    }
}
