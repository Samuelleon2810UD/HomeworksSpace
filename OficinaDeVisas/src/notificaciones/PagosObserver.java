package notificaciones;

import solicitudes.Solicitud;
import solicitudes.SolicitudObserver;
import util.Consola;

/** Procesa el reembolso en la caja cuando una solicitud se cancela. */
public class PagosObserver implements SolicitudObserver {

    private static final String ACTOR = "PagosObserver";

    private int reembolsosProcesados = 0;

    @Override
    public void alCancelarse(Solicitud solicitud) {
        reembolsosProcesados++;
        Consola.registrar(ACTOR, "Reembolso procesado para " + solicitud.getViajero());
    }

    public int getReembolsosProcesados() {
        return reembolsosProcesados;
    }
}
