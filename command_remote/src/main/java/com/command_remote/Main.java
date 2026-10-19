package com.command_remote;

import com.command_remote.Command.ActionCommand;
import com.command_remote.Command.TurnOfAllDevicesCommand;
import com.command_remote.Command.TurnOnAllDevicesCommand;
import com.command_remote.Devices.Cafetera;
import com.command_remote.Devices.Exprimidor;
import com.command_remote.Devices.Tostadora;
import com.command_remote.Remote.RemoteMain;

public class Main {
    public static void main(String[] args) {
        Cafetera cafetera = new Cafetera();
        Tostadora tostadora = new Tostadora();
        Exprimidor exprimidor = new Exprimidor();
        RemoteMain remote = new RemoteMain();

        remote.setCommandSlot(1, new TurnOnAllDevicesCommand(cafetera, tostadora, exprimidor));
        remote.setCommandSlot(2, new ActionCommand(cafetera::makeCoffee));
        remote.setCommandSlot(3, new ActionCommand(exprimidor::makeJuice));
        remote.setCommandSlot(4, new ActionCommand(tostadora::makeToast));

        System.out.println("\n--- Encender todos y preparar desayuno ---");
        pressButton(remote, 1);
        pressButton(remote, 2);
        pressButton(remote, 3);
        pressButton(remote, 4);

        System.out.println("\n--- Reprogramar el control remoto ---");
        remote.setCommandSlot(2, new ActionCommand(cafetera::makeEspresso));
        remote.setCommandSlot(3, new ActionCommand(tostadora::makeBagel));
        remote.setCommandSlot(4, new ActionCommand(cafetera::stop));
        pressButton(remote, 2);
        pressButton(remote, 3);
        pressButton(remote, 4);

        remote.setCommandSlot(2, new ActionCommand(tostadora::stop));
        remote.setCommandSlot(3, new ActionCommand(exprimidor::stop));
        pressButton(remote, 2);
        pressButton(remote, 3);

        System.out.println("\n--- Apagar todos los dispositivos ---");
        remote.setCommandSlot(1, new TurnOfAllDevicesCommand(cafetera, tostadora, exprimidor));
        pressButton(remote, 1);
    }

    private static void pressButton(RemoteMain remote, int slot) {
        System.out.println("Botón " + slot + ":");
        remote.pressButton(slot);
    }
}