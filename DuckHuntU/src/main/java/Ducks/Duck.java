/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ducks;

import Behavior.Fly.FlyBehavior;
import Behavior.Sound.SoundBehavior;

/**
 *
 * @author Estudiantes
 */
public abstract class Duck {
    
    protected FlyBehavior flyBehavior;
    protected SoundBehavior soundBehavior;

    public void Swim(){
        System.out.print("The duck is Swiming");
    }
    
    public void Hi(){
        System.out.print("Duck say Hi");
    }

    public void flyPerform(){
        this.flyBehavior.Fly();
    }

    public void SoundPerform(){
        this.soundBehavior.Sound();
    }

    public void ChangeFlyBehavior(FlyBehavior fb){
        this.flyBehavior = fb;
    }

    public void ChangeSoundBehavior(SoundBehavior sb){
        this.soundBehavior = sb;
    }

    public abstract void display();
}
