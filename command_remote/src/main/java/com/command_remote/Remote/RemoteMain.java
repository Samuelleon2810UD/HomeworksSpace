package com.command_remote.Remote;

import com.command_remote.Command.Command;

public class RemoteMain {
    public static final int SLOT_COUNT = 4;

    private final Command[] slots;

    public RemoteMain() {
        this.slots = new Command[SLOT_COUNT];
    }

    public void setCommandSlot(int slot, Command command) {
        validateSlot(slot);
        this.slots[slot - 1] = command;
    }

    public void setCommandSlot1(Command command) {
        setCommandSlot(1, command);
    }

    public void setCommandSlot2(Command command) {
        setCommandSlot(2, command);
    }

    public void pressButton(int slot) {
        validateSlot(slot);
        Command command = this.slots[slot - 1];
        if (command != null) {
            command.execute();
        } else {
            System.out.println("No hay comando asignado al slot " + slot);
        }
    }

    public void pressButtonSlot1() {
        pressButton(1);
    }

    public void pressButtonSlot2() {
        pressButton(2);
    }

    private void validateSlot(int slot) {
        if (slot < 1 || slot > SLOT_COUNT) {
            throw new IllegalArgumentException(
                    "El slot debe estar entre 1 y " + SLOT_COUNT + ": " + slot);
        }
    }
}
