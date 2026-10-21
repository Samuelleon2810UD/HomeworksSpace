package com.command_bank.Remote;

import com.command_bank.Command.Command;

public class AutomaticAtm {
        public static final int SLOT_COUNT = 4;

    private final Command[] slots;

    public AutomaticAtm() {
        this.slots = new Command[SLOT_COUNT];
    }

    public void setCommandSlot(int slot, Command command) {
        validateSlot(slot);
        this.slots[slot - 1] = command;
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

    private void validateSlot(int slot) {
        if (slot < 1 || slot > SLOT_COUNT) {
            throw new IllegalArgumentException(
                    "El slot debe estar entre 1 y " + SLOT_COUNT + ": " + slot);
        }
    }
}
