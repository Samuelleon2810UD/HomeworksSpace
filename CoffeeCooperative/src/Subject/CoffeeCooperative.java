/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Subject;

import Extras.Lotes;
import Observer.Barist;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Estudiantes
 */
public class CoffeeCooperative implements Subject {
    // Antes: "private List<Lotes> l;" y "private List<Barist> o;" sin inicializar
    // -> AddBarist/AddLote lanzaban NullPointerException al primer uso.
    private List<Lotes> l = new ArrayList<>();
    private List<Barist> o = new ArrayList<>();
    private Lotes ultimoLoteAgregado;

    @Override
    public void AddBarist(Barist b) {
        this.o.add(b);
    }

    @Override
    public void RemoveBarist(Barist b) {
        this.o.remove(b);
    }

    @Override
    public void InformBarist() {
        for (Barist b : o) {
            // Cada barista es notificado según la modalidad con la que se suscribió.
            switch (b.getModalidad()) {
                case PUSH_COMPLETO:
                    b.update(this.l);
                    break;
                case PUSH_INCREMENTAL:
                    if (ultimoLoteAgregado != null) {
                        b.update(ultimoLoteAgregado);
                    }
                    break;
                case PULL:
                    b.update((Subject) this);
                    break;
            }
        }
    }

    public void AddLote(Lotes lote){
        this.l.add(lote);
        this.ultimoLoteAgregado = lote;
    }

    // Se agrega: implementación del getLotes() que exige la interfaz Subject (modalidad PULL).
    @Override
    public List<Lotes> getLotes() {
        return this.l;
    }

    public List<Barist> getBaristas() {
        return this.o;
    }

}
