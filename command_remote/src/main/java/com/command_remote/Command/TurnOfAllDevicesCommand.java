package com.command_remote.Command;

import com.command_remote.Devices.Cafetera;
import com.command_remote.Devices.Exprimidor;
import com.command_remote.Devices.Tostadora;

public class TurnOfAllDevicesCommand implements Command {
    private Cafetera cafetera;
    private Tostadora tostadora;
    private Exprimidor exprimidor;

    public TurnOfAllDevicesCommand(Cafetera cafetera, Tostadora tostadora, Exprimidor exprimidor) {
        this.cafetera = cafetera;
        this.tostadora = tostadora;
        this.exprimidor = exprimidor;
    }

    @Override
    public void execute() {
        cafetera.off();
        tostadora.off();
        exprimidor.off();
    }
    
}
