package com.command_bank.Command;

import com.command_bank.Devices.CuentaBancaria;

public class DepositarCommand implements Command {
    private final CuentaBancaria cuenta;
    private final double cantidad;
    private boolean ejecutado;

    public DepositarCommand(CuentaBancaria cuenta, double cantidad) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
    }

    @Override
    public void execute() {
        if (ejecutado) {
            System.out.println("El depósito de este comando ya fue ejecutado.");
            return;
        }
        ejecutado = cuenta.depositar(cantidad);
    }

    @Override
    public void undo() {
        if (!ejecutado) {
            System.out.println("No hay un depósito ejecutado por este comando para deshacer.");
            return;
        }
        ejecutado = !cuenta.undoDeposito(cantidad);
    }
}
