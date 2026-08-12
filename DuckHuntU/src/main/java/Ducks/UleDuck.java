/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ducks;

import Behavior.Fly.NoFly;
import Behavior.Sound.ArtificialSoundQuack;

/**
 *
 * @author Estudiantes
 */
public class UleDuck extends Duck{
    
    public UleDuck(){
        this.flyBehavior = new NoFly();
        this.soundBehavior = new ArtificialSoundQuack();
    }

    @Override
    public void display(){
        System.out.print("I'm a no real duck, but I says Quack\n");
    }
}
