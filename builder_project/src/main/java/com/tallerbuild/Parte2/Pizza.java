package com.tallerbuild.Parte2;

public class Pizza implements BuilderPizza{

    // execute
    public static void main(String[] args) {
        Pizza pizza = new Pizza();
        pizza.buildMasa("normal");
        pizza.buildTamaño("grande");
        pizza.buildQueso(true);
        pizza.buildPepperoni(true);
        pizza.buildChampiñones(false);
        pizza.buildPiña(false);
        pizza.buildSalsa("tomate");
        pizza.mostrarPizza();
    }

    // attributes
    private String masa;
    private String tamanio;
    private String queso;
    private String pepperoni;
    private String champiñones;
    private String piña;
    private String salsa;

    // methods
    @Override
    public void buildMasa(String masa) {
        this.masa = masa;
    }

    @Override
    public void buildTamaño(String tamanio) {
        this.tamanio = tamanio;
    }

    @Override
    public void buildQueso(boolean queso) {
        this.queso = queso ? "con queso" : "sin queso";
    }

    @Override
    public void buildPepperoni(boolean pepperoni) {
        this.pepperoni = pepperoni ? "con pepperoni" : "sin pepperoni";
    }

    @Override
    public void buildChampiñones(boolean champiñones) {
        this.champiñones = champiñones ? "con champiñones" : "sin champiñones";
    }

    @Override
    public void buildPiña(boolean piña) {
        this.piña = piña ? "con piña" : "sin piña";
    }

    @Override
    public void buildSalsa(String salsa) {
        this.salsa = salsa;
    }

    public void mostrarPizza() {
        System.out.println("Pizza construida:");
        System.out.println("Masa: " + masa);
        System.out.println("Tamaño: " + tamanio);
        System.out.println("Queso: " + queso);
        System.out.println("Pepperoni: " + pepperoni);
        System.out.println("Champiñones: " + champiñones);
        System.out.println("Piña: " + piña);
        System.out.println("Salsa: " + salsa);
    }
}


