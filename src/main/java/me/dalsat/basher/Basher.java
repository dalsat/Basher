package me.dalsat.basher;

import me.dalsat.basher.command.ActionExecutor;
import me.dalsat.basher.command.Command;
import me.dalsat.basher.input.Console;
import me.dalsat.basher.store.Stores;
import me.dalsat.basher.store.core.Store;

import java.io.InputStream;


/**
 * Main entry point for the application.
 * Provides the methods to start/stop the application, and implements the
 * cycle read->interpret->execute->feedback.
 */
public enum Basher {

    INSTANCE;

    private Console console;
    private ActionExecutor actionPerformer = new ActionExecutor();
    private boolean running = false;

    private Store store = Stores.getStore();

    Basher() {
        setInput(System.in);
    }

    public void setInput(InputStream is) {
        console = new Console(is);
    }

    private String banner() {
        return "Welcome to Basher: the command line social network.";
    }
    private String byeBanner() {
        return "Quitting Basher. Goodbye!";
    }

    private void run() {

        console.println(banner());
        while(running) {
            console.nextCommand().ifPresentOrElse(
                    this::executeCommand,
                    () -> console.println("invalid command")
            );
        }
        console.println(byeBanner());
    }

    public Store getStore() {
        return store;
    }

    private void executeCommand(Command command) {
        console.println(actionPerformer.execute(command, store));
    }


    public static void start() {
        INSTANCE.running = true;
        INSTANCE.run();
    }

    public static void stop() {
        INSTANCE.running = false;
    }

    public static void main(String[] args) {
        Basher.start();
    }
}
