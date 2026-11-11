package notificaciones;

import solicitudes.Solicitud;
import solicitudes.SolicitudObserver;
import util.Consola;

/** Avisa por correo cuando una solicitud se cancela. */
public class CorreoObserver implements SolicitudObserver {

    private static final String ACTOR = "CorreoObserver";

    @Override
    public void alCancelarse(Solicitud solicitud) {
        Consola.registrar(ACTOR, "Aviso de cancelacion enviado a " + solicitud.getViajero());
    }
}
