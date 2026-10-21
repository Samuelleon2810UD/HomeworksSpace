package com.command_bank.Command;

import com.command_bank.Devices.CuentaBancaria;

public class TransferirDineroCommand implements Command {
    private final CuentaBancaria cuentaOrigen;
    private final CuentaBancaria cuentaDestino;
    private final double cantidad;
    private boolean ejecutada;

    public TransferirDineroCommand(CuentaBancaria cuentaOrigen, CuentaBancaria cuentaDestino, double cantidad) {
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.cantidad = cantidad;
    }

    @Override
    public void execute() {
        if (ejecutada) {
            System.out.println("La transferencia de este comando ya fue ejecutada.");
            return;
        }
        ejecutada = cuentaOrigen.transferirSaldo(cuentaDestino, cantidad);
    }

    @Override
    public void undo() {
        if (!ejecutada) {
            System.out.println("No hay una transferencia ejecutada por este comando para deshacer.");
            return;
        }
        ejecutada = !cuentaOrigen.undoTransferencia(cuentaDestino, cantidad);
    }
}
