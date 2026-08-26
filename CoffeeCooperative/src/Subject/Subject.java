/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Subject;

import Extras.Lotes;
import Observer.Barist;
import java.util.List;

/**
 *
 * @author Estudiantes
 */
public interface Subject {
    public void AddBarist(Barist b);
    public void RemoveBarist(Barist b);
    public void InformBarist();
    // Se agrega: sin este getter, la modalidad PULL no tiene forma de consultar datos.
    public List<Lotes> getLotes();
}
