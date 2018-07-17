package me.dalsat.basher.input;

import me.dalsat.basher.command.Command;
import me.dalsat.basher.input.parser.InputParser;
import me.dalsat.basher.input.parser.RegexInputParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

public class Console {

    private BufferedReader source;
    private String prompt = "basher~> ";

    private InputParser parser = new RegexInputParser();

    public Console(InputStream is) {
        source = new BufferedReader(new InputStreamReader(is));
    }

    public Optional<Command> nextCommand() {

        System.out.print(prompt);
        try {
            return parser.parseCommand(source.readLine());
        }
        catch (IOException e) {
            return Optional.empty();
        }
    }

}
