package consulado;

import util.Consola;

/** Unica responsabilidad: enviar el correo de avance del tramite. */
public class NotificadorDeCorreo {

    private static final String ACTOR = "NotificadorDeCorreo";

    public void avisarVisaEnCamino(String viajero) {
        Consola.registrar(ACTOR, "Correo para " + viajero + ": su visa va en camino");
    }
}
