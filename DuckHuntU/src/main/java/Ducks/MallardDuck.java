/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ducks;

import Behavior.Fly.FlyWithSuperMegaJetPack;
import Behavior.Sound.SoundQuack;

/**
 *
 * @author Estudiantes
 */
public class MallardDuck extends Duck {

    public MallardDuck(){
        this.flyBehavior  = new FlyWithSuperMegaJetPack();
        this.soundBehavior = new SoundQuack();
    }


    @Override
    public void display(){
        System.out.print("I'm a coffee duck\n");
    }
}
