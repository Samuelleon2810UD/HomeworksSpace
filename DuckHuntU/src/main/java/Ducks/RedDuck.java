/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ducks;


import Behavior.Fly.FlyWithWings;
import Behavior.Sound.SoundQuack;

/**
 *
 * @author Estudiantes
 */
public class RedDuck extends Duck{

    public RedDuck(){
        this.soundBehavior = new SoundQuack();
        this.flyBehavior = new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.print("I'm Red duck my friend\n");
    }
}
