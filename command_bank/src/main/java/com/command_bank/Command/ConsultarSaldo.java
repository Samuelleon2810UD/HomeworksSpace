package com.command_bank.Command;

import com.command_bank.Devices.CuentaBancaria;

public class ConsultarSaldo implements Command {
    private CuentaBancaria cuenta;

    public ConsultarSaldo(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public void execute() {
        double saldo = cuenta.consultarSaldo();
        System.out.println("Saldo actual: " + saldo);
    }

    @Override
    public void undo() {
        
    }
    
}
