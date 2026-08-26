/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Observer;

import Extras.Lotes;
import Subject.Subject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Estudiantes
 */
public class Barist implements ObserverCooperative {

    private List<Lotes> actualLotes;
    private String name;
    private int idNumber;
    // Se agrega: cada barista queda suscrito con UNA modalidad de notificación.
    private ModalidadNotificacion modalidad;

    public Barist(String n , int id, ModalidadNotificacion modalidad){
        this.name = n;
        this.idNumber = id;
        this.modalidad = modalidad;
        this.actualLotes = new ArrayList<>(); // antes quedaba null hasta el primer update()
    }

    @Override
    public void update(List<Lotes> lotesCompletos) {
        this.actualLotes = lotesCompletos;
        System.out.println("  -> [PUSH COMPLETO] " + name + " recibió la lista completa ("
                + lotesCompletos.size() + " lote(s)).");
    }

    @Override
    public void update(Lotes nuevoLote) {
        if (this.actualLotes == null) {
            this.actualLotes = new ArrayList<>();
        }
        this.actualLotes.add(nuevoLote);
        System.out.println("  -> [PUSH INCREMENTAL] " + name + " fue notificado del nuevo lote: "
                + nuevoLote.getVarietal() + " (" + nuevoLote.getOrigin() + ").");
    }

    @Override
    public void update(Subject sujeto) {
        this.actualLotes = sujeto.getLotes();
        System.out.println("  -> [PULL] " + name + " fue avisado del cambio y consultó al sujeto; "
                + "ahora tiene " + actualLotes.size() + " lote(s).");
    }

    public String getName() {
        return name;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public ModalidadNotificacion getModalidad() {
        return modalidad;
    }

    public List<Lotes> getActualLotes() {
        return actualLotes;
    }

}
