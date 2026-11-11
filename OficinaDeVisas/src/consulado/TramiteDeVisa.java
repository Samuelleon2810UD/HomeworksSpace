package consulado;

import util.Consola;

/**
 * Unica responsabilidad: coordinar el orden del tramite.
 * No valida, no cobra, no guarda y no escribe correos: delega cada tarea en
 * su colaborador (inyectado por constructor, por lo que es facil de reemplazar).
 */
public class TramiteDeVisa {

    private static final String ACTOR = "TramiteDeVisa";

    private final ValidadorDePasaporte validador;
    private final CajaDePagos caja;
    private final ArchivoDeExpedientes archivo;
    private final NotificadorDeCorreo notificador;

    public TramiteDeVisa(ValidadorDePasaporte validador, CajaDePagos caja,
                         ArchivoDeExpedientes archivo, NotificadorDeCorreo notificador) {
        this.validador = validador;
        this.caja = caja;
        this.archivo = archivo;
        this.notificador = notificador;
    }

    public boolean tramitar(String viajero, String pasaporte, double precio) {
        if (!validador.esValido(pasaporte)) {
            Consola.registrar(ACTOR, "Tramite detenido para " + viajero);
            return false;
        }
        caja.cobrar(viajero, precio);
        archivo.guardar(viajero);
        notificador.avisarVisaEnCamino(viajero);
        return true;
    }
}
