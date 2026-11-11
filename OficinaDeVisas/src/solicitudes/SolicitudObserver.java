package solicitudes;

/** Observer: cualquiera que quiera enterarse de la cancelacion implementa esta interfaz. */
public interface SolicitudObserver {

    void alCancelarse(Solicitud solicitud);
}
