package com.command_bank;

import com.command_bank.Command.ConsultarSaldo;
import com.command_bank.Command.DepositarCommand;
import com.command_bank.Command.RetirarCommand;
import com.command_bank.Command.TransferirDineroCommand;
import com.command_bank.Devices.CuentaBancaria;
import com.command_bank.Remote.AutomaticAtm;

public class Main {
    public static void main(String[] args) {
        CuentaBancaria cuentaPrincipal = new CuentaBancaria("1001", "1234");
        CuentaBancaria cuentaDestino = new CuentaBancaria("2002", "5678");
        AutomaticAtm cajero = new AutomaticAtm();

        cajero.setCommandSlot(1, new DepositarCommand(cuentaPrincipal, 1000));
        cajero.setCommandSlot(2, new RetirarCommand(cuentaPrincipal, 150));
        cajero.setCommandSlot(3, new TransferirDineroCommand(cuentaPrincipal, cuentaDestino, 200));
        cajero.setCommandSlot(4, new ConsultarSaldo(cuentaPrincipal));

        System.out.println("=== Operaciones por medio del cajero automático ===");
        System.out.println("Operación 1: depósito de $1000");
        cajero.pressButton(1);

        System.out.println("\nOperación 2: retiro de $150");
        cajero.pressButton(2);

        System.out.println("\nOperación 3: transferencia de $200 a la cuenta 2002");
        cajero.pressButton(3);

        System.out.println("\nOperación 4: consulta del saldo de la cuenta principal");
        cajero.pressButton(4);

        System.out.println("Saldo de la cuenta destino: " + cuentaDestino.consultarSaldo());
    }
}