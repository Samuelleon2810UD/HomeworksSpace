package com.command_bank.Devices;

import java.util.ArrayDeque;
import java.util.Deque;

public class CuentaBancaria {
    private double saldo;
    private String numeroCuenta;
    private String password;
    private final Deque<Transaccion> historial = new ArrayDeque<>();

    private enum TipoTransaccion {
        DEPOSITO,
        RETIRO,
        TRANSFERENCIA
    }

    private record Transaccion(TipoTransaccion tipo, double cantidad, CuentaBancaria cuentaDestino) {
    }

    public CuentaBancaria(String numeroCuenta, String password) {
        this.numeroCuenta = numeroCuenta;
        this.password = password;
        this.saldo = 0.0;
    }

    public void validateClient(String numeroCuenta, String password) {
        if (this.numeroCuenta.equals(numeroCuenta) && this.password.equals(password)) {
            System.out.println("Cliente validado correctamente.");
        } else {
            System.out.println("Número de cuenta o contraseña incorrectos.");
        }
    }

    public boolean depositar(double cantidad) {
        if (esCantidadValida(cantidad) && Double.isFinite(saldo + cantidad)) {
            saldo += cantidad;
            historial.push(new Transaccion(TipoTransaccion.DEPOSITO, cantidad, null));
            System.out.println("Depósito exitoso. Nuevo saldo: " + saldo);
            return true;
        } else {
            System.out.println("Cantidad inválida para depósito.");
            return false;
        }
    }

    public boolean retirar(double cantidad) {
        if (esCantidadValida(cantidad) && cantidad <= saldo) {
            saldo -= cantidad;
            historial.push(new Transaccion(TipoTransaccion.RETIRO, cantidad, null));
            System.out.println("Retiro exitoso. Nuevo saldo: " + saldo);
            return true;
        } else {
            System.out.println("Cantidad inválida para retiro o saldo insuficiente.");
            return false;
        }
    }

    public double consultarSaldo() {
        return saldo;
    }

    public boolean transferirSaldo(CuentaBancaria cuentaDestino, double cantidad) {
        if (cuentaDestino != null && cuentaDestino != this
                && esCantidadValida(cantidad) && cantidad <= saldo
                && Double.isFinite(cuentaDestino.saldo + cantidad)) {
            saldo -= cantidad;
            cuentaDestino.saldo += cantidad;
            historial.push(new Transaccion(TipoTransaccion.TRANSFERENCIA, cantidad, cuentaDestino));
            System.out.println("Transferencia exitosa. Nuevo saldo: " + saldo);
            return true;
        } else {
            System.out.println("Cantidad inválida para transferencia o saldo insuficiente.");
            return false;
        }
    }

    public boolean undoRetiro(double cantidad) {
        return deshacer(TipoTransaccion.RETIRO, cantidad, null);
    }

    public boolean undoDeposito(double cantidad) {
        return deshacer(TipoTransaccion.DEPOSITO, cantidad, null);
    }

    public boolean undoTransferencia(CuentaBancaria cuentaDestino, double cantidad) {
        return deshacer(TipoTransaccion.TRANSFERENCIA, cantidad, cuentaDestino);
    }

    public boolean deshacerUltimaTransaccion() {
        return deshacer(null, 0, null);
    }

    private boolean deshacer(TipoTransaccion tipoEsperado, double cantidadEsperada,
                             CuentaBancaria destinoEsperado) {
        Transaccion transaccion = historial.peek();
        if (transaccion == null) {
            System.out.println("No hay transacciones para deshacer.");
            return false;
        }

        if (tipoEsperado != null && (transaccion.tipo() != tipoEsperado
                || Double.compare(transaccion.cantidad(), cantidadEsperada) != 0
                || (tipoEsperado == TipoTransaccion.TRANSFERENCIA
                && transaccion.cuentaDestino() != destinoEsperado))) {
            System.out.println("La última transacción no coincide con la operación indicada.");
            return false;
        }

        switch (transaccion.tipo()) {
            case DEPOSITO -> {
                if (transaccion.cantidad() > saldo) {
                    System.out.println("No se puede deshacer el depósito, saldo insuficiente.");
                    return false;
                }
                saldo -= transaccion.cantidad();
            }
            case RETIRO -> {
                if (!Double.isFinite(saldo + transaccion.cantidad())) {
                    System.out.println("No se puede deshacer el retiro, el saldo excedería el límite permitido.");
                    return false;
                }
                saldo += transaccion.cantidad();
            }
            case TRANSFERENCIA -> {
                CuentaBancaria destino = transaccion.cuentaDestino();
                if (transaccion.cantidad() > destino.saldo) {
                    System.out.println("No se puede deshacer la transferencia, saldo insuficiente en la cuenta destino.");
                    return false;
                }
                if (!Double.isFinite(saldo + transaccion.cantidad())) {
                    System.out.println("No se puede deshacer la transferencia, el saldo de origen excedería el límite permitido.");
                    return false;
                }
                saldo += transaccion.cantidad();
                destino.saldo -= transaccion.cantidad();
            }
        }

        historial.pop();
        System.out.println("Transacción deshecha. Nuevo saldo: " + saldo);
        return true;
    }

    private boolean esCantidadValida(double cantidad) {
        return Double.isFinite(cantidad) && cantidad > 0;
    }
}
