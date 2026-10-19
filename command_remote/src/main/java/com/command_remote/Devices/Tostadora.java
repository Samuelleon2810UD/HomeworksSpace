package com.command_remote.Devices;

public class Tostadora {
    private boolean encendida;
    private String status;
    private boolean isWorking;

    public Tostadora() {
        this.encendida = false;
        this.isWorking = false;
        this.status = "Tostadora apagada";
    }

    public void on() {
        this.encendida = true;
        this.status = "Tostadora encendida";
        System.out.println(status);
    }

    public void off() {
        if(this.isWorking) {
            stop();
        }

        if(this.encendida) {
            this.encendida = false;
            this.status = "Tostadora apagada";
        } else {
            this.status = "Tostadora ya estaba apagada";
        }
        System.out.println(status);
    }

    public void makeToast() {
        if (this.encendida) {
            this.isWorking = true;
            this.status = "Tostadora haciendo tostadas";
        } else {
            this.status = "Tostadora apagada, no se puede hacer tostadas";
        }
        System.out.println(status);
    }

    public void makeBagel() {
        if (this.encendida) {
            this.isWorking = true;
            this.status = "Tostadora haciendo bagels";
        } else {
            this.status = "Tostadora apagada, no se puede hacer bagels";
        }
        System.out.println(status);
    }

    public void stop() {
        this.isWorking = false;
        if (this.encendida) {
            this.status = "Tostadora detenida";
        } else {
            this.status = "Tostadora apagada, no se puede detener";
        }
        System.out.println(status);
    }

    public String getStatus() {
        return this.status;
    }
}
