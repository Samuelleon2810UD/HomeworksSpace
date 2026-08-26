/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Observer;

import Extras.Lotes;
import Subject.Subject;
import java.util.List;

/**
 * Antes solo existía update(List<Lotes>), lo que solo permitía la modalidad
 * "push completo". Se agregan dos firmas más para soportar push incremental
 * y pull.
 *
 * @author Estudiantes
 */
public interface ObserverCooperative {

    /** Push completo: el Subject envía toda la lista de lotes vigente. */
    public void update(List<Lotes> lotesCompletos);

    /** Push incremental: el Subject envía solo el lote que se acaba de agregar. */
    public void update(Lotes nuevoLote);

    /** Pull: el Subject solo notifica el cambio; el observador consulta los datos él mismo. */
    public void update(Subject sujeto);
}
