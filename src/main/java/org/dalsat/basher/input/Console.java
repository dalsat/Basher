package org.dalsat.basher.input;

import org.dalsat.basher.command.Command;
import org.dalsat.basher.input.parser.InputParser;
import org.dalsat.basher.input.parser.RegexInputParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

public class Console {

    private BufferedReader source;

    private InputParser parser = new RegexInputParser();

    public Console(InputStream is) {
        source = new BufferedReader(new InputStreamReader(is));
    }

    public Optional<Command> nextCommand() {

        try {
            var line = source.readLine();
            return parser.parseCommand(line);
        }
        catch (IOException e) {
            return Optional.empty();
        }
    }
}
