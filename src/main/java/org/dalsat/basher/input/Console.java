package org.dalsat.basher.input;

import org.dalsat.basher.command.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Console {

    BufferedReader source;

    public Console(InputStream is) {
        source = new BufferedReader(new InputStreamReader(is));
    }

    public Command nextCommand() throws IOException {

        var line = source.readLine();

        var tokenizer = new StringTokenizer(line);

        if (!tokenizer.hasMoreTokens()) {
            throw new InvalidCommandException(line);
        }

        var newCommand = new Command(tokenizer.nextToken());

        if (tokenizer.hasMoreTokens()) {
            newCommand.setAction(tokenizer.nextToken());
        }

        if (tokenizer.hasMoreElements()) {
            var parameter = new StringBuilder();
            while (tokenizer.hasMoreTokens()) {
                parameter.append(tokenizer.nextToken());
            }
            newCommand.setParameter(parameter.toString());
        }

        return newCommand;
    }
}
