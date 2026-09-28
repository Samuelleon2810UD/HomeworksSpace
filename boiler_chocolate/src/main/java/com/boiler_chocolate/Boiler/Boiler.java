package com.boiler_chocolate.Boiler;

public class Boiler {
    private float content;
    private boolean resistance;
    private boolean isFilled;

    private Boiler() {
        this.content = 0;
        this.resistance = false;
        this.isFilled = false;
    }

    public static Boiler getBoiler() {
        return new Boiler();
    }

    public void fill(float amount) {
        if(this.resistance) {
            System.out.println("Cannot fill the boiler while the resistance is on.");
            return;
        }

        this.content += amount;
        this.isFilled = true;
        System.out.println("Boiler filled with " + amount + " units. Current content: " + this.content);
    }

    public void mix() {
        if(!this.isFilled) {
            System.out.println("Cannot mix. The boiler is empty.");
            return;
        }

        if(this.resistance) {
            System.out.println("Cannot mix while the resistance is on.");
            return;
        }

        this.resistance = true;
        System.out.println("Mixing the contents of the boiler...");
    }

    public void stopMixing() {
        if(!this.resistance) {
            System.out.println("The resistance is already off.");
            return;
        }

        this.resistance = false;
        System.out.println("Stopped mixing. The resistance is now off.");
    }

    public void FilledOut() {
        if(!this.isFilled) {
            System.out.println("Cannot empty the boiler. It is already empty.");
            return;
        }

        if(this.resistance) {
            System.out.println("Cannot empty the boiler while the resistance is on.");
            return;
        }

        this.content = 0;
        this.isFilled = false;
        System.out.println("Boiler emptied.");
    }
}
