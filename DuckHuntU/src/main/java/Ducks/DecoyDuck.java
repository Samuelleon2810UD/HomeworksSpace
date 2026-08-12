/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ducks;

import Behavior.Fly.FlyWithWings;
import Behavior.Sound.NoSound;

/**
 *
 * @author Estudiantes
 */
public class DecoyDuck extends Duck {

    public DecoyDuck(){
        this.flyBehavior = new FlyWithWings();
        this.soundBehavior = new NoSound();
    }

    @Override
    public void display(){
        System.out.print("Im a decoy duck bruh\n");
    }
}
