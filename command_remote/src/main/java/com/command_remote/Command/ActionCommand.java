package com.command_remote.Command;

import java.util.Objects;

public class ActionCommand implements Command {
    private final Runnable action;

    public ActionCommand(Runnable action) {
        this.action = Objects.requireNonNull(action, "La acción no puede ser null");
    }

    @Override
    public void execute() {
        this.action.run();
    }
}
