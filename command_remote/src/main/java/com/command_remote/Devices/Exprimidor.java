package com.command_remote.Devices;

public class Exprimidor {
    private boolean encendido;
    private String status;
    private boolean isWorking;

    public Exprimidor() {
        this.encendido = false;
        this.status = "Exprimidor apagado";
    }

    public void on() {
        this.encendido = true;
        this.status = "Exprimidor encendido";
        System.out.println(status);
    }

    public void off() {
        if(this.isWorking) {
            stop();
        }
        if(this.encendido) {
            this.encendido = false;
            this.status = "Exprimidor apagado";
        } else {
            this.status = "Exprimidor ya estaba apagado";
        }
        System.out.println(status);
    }

    public void makeJuice() {
        if (this.encendido) {
            this.isWorking = true;
            this.status = "Exprimidor haciendo jugo";
        } else {
            this.status = "Exprimidor apagado, no se puede hacer jugo";
        }
        System.out.println(status);
    }

    public void stop() {
        this.isWorking = false;
        if (this.encendido) {
            this.status = "Exprimidor detenido";
        } else {
            this.status = "Exprimidor apagado, no se puede detener";
        }
        System.out.println(status);
    }

    public String getStatus() {
        return this.status;
    }
}
