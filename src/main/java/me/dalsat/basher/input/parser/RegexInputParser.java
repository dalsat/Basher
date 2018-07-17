package me.dalsat.basher.input.parser;

import me.dalsat.basher.command.Command;

import java.util.Optional;
import java.util.regex.Pattern;

public class RegexInputParser implements InputParser {

    private static final String PARSING_REGEX = "(\\S+)\\s*(\\S*)\\s*(.*)";
    private Pattern pattern = Pattern.compile(PARSING_REGEX);

    @Override
    public Optional<Command> parseCommand(String line) {
        var matcher = pattern.matcher(line);
        if (matcher.find()) {
            var newCommand = Command.forUser(matcher.group(1));
            newCommand.setAction(matcher.group(2));
            newCommand.setParameter(matcher.group(3));

            return Optional.of(newCommand);
        } else {
            return Optional.empty();
        }
    }
}
