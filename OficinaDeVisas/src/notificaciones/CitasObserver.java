package notificaciones;

import solicitudes.Solicitud;
import solicitudes.SolicitudObserver;
import util.Consola;

/** Libera el cupo de la agenda de citas cuando una solicitud se cancela. */
public class CitasObserver implements SolicitudObserver {

    private static final String ACTOR = "CitasObserver";

    @Override
    public void alCancelarse(Solicitud solicitud) {
        Consola.registrar(ACTOR, "Cupo de la cita de " + solicitud.getViajero() + " liberado");
    }
}
