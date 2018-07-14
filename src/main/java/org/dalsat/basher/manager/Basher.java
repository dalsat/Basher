package org.dalsat.basher.manager;

import org.dalsat.basher.command.Command;
import org.dalsat.basher.command.ActionExecutor;
import org.dalsat.basher.input.Console;
import org.dalsat.basher.store.Stores;
import org.dalsat.basher.store.core.Store;

import java.io.InputStream;

public enum Basher {

    INSTANCE;

    private Console console;
    private ActionExecutor actionPerformer = new ActionExecutor();
    private boolean running = false;

    private Store store = Stores.newMemoryStore();

    Basher() {
        setInput(System.in);
    }

    public void setInput(InputStream is) {
        console = new Console(is);
    }

    private void loop() {

        System.out.println("Listening...");
        while(running) {
            console.nextCommand().ifPresentOrElse(
                    this::executeCommand,
                    () -> System.out.println("invalid command")
            );
        }
    }

    public Store getStore() {
        return store;
    }

    private void executeCommand(Command command) {
        actionPerformer.execute(command, store);
    }

    public static void start() {
        INSTANCE.running = true;
        INSTANCE.loop();
    }

    public static void stop() {
        INSTANCE.running = false;
    }

    public static void main(String[] args) {
        Basher.start();
    }
}
