package com.command_remote.Devices;


public class Cafetera {
    private boolean encendida;
    private String status;
    private boolean isWorking;


    public Cafetera() {
        this.encendida = false;
        this.isWorking = false;
        this.status = "Cafetera apagada";
        System.out.println(status);
    }

    public void on() {
        this.encendida = true;
        this.status = "Cafetera encendida";
        System.out.println(status);
    }

    public void off() {
        if(this.isWorking) {
            stop();
        }

        if(this.encendida) {
            this.encendida = false;
            this.status = "Cafetera apagada";
        } else {
            this.status = "Cafetera ya estaba apagada";
        }
        System.out.println(status);
    }

    public void makeCoffee() {
        if (this.encendida) {
            this.isWorking = true;
            this.status = "Cafetera haciendo café";
        } else {
            this.status = "Cafetera apagada, no se puede hacer café";
        }
        System.out.println(status);
    }

    public void makeEspresso() {
        if (this.encendida) {
            this.isWorking = true;
            this.status = "Cafetera haciendo espresso";
        } else {
            this.status = "Cafetera apagada, no se puede hacer espresso";
        }
        System.out.println(status);
    }
    

    public void stop() {
        this.isWorking = false;
        if (this.encendida) {
            this.status = "Cafetera detenida";
        } else {
            this.status = "Cafetera apagada, no se puede detener";
        }
        System.out.println(status);
    }

    public String getStatus() {
        return this.status;
    }
}