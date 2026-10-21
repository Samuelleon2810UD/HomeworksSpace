package com.command_bank.Command;

import com.command_bank.Devices.CuentaBancaria;

public class RetirarCommand implements Command {
    private final CuentaBancaria cuenta;
    private final double monto;
    private boolean ejecutado;

    public RetirarCommand(CuentaBancaria cuenta, double monto) {
        this.cuenta = cuenta;
        this.monto = monto;
    }

    @Override
    public void execute() {
        if (ejecutado) {
            System.out.println("El retiro de este comando ya fue ejecutado.");
            return;
        }
        ejecutado = cuenta.retirar(monto);
    }

    @Override
    public void undo() {
        if (!ejecutado) {
            System.out.println("No hay un retiro ejecutado por este comando para deshacer.");
            return;
        }
        ejecutado = !cuenta.undoRetiro(monto);
    }
}
