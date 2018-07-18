package me.dalsat.basher.input;

import me.dalsat.basher.command.Command;
import me.dalsat.basher.input.parser.InputParser;
import me.dalsat.basher.input.parser.RegexInputParser;

import java.io.*;
import java.util.Optional;

/**
 * Provides the interface to interact with the console.
 * The method <code>nextCommand</code> reads from the input source and returns
 * a string containing the command.
 *
 * The method <code>println</code> writes output stream.
 * By default it targets the standard input and the standard output, but both
 * can be rerouted to read or write to different streams.
 */
public class Console {

    private BufferedReader source;
    private PrintStream outStream = System.out;
    private String prompt = "basher~> ";

    private InputParser parser = new RegexInputParser();

    public Console(InputStream is) {
        source = new BufferedReader(new InputStreamReader(is));
    }

    public void setOutStream(PrintStream outStream) {
        this.outStream = outStream;
    }

    public Optional<Command> nextCommand() {

        outStream.print(prompt);
        try {
            return parser.parseCommand(source.readLine());
        }
        catch (IOException e) {
            return Optional.empty();
        }
    }

    public void println(String line) {
        if (!line.isEmpty()) {
            outStream.println(line);
        }
    }

}
