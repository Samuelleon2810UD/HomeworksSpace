package solicitudes;

import java.util.ArrayList;
import java.util.List;
import visas.TipoVisa;

/**
 * Sujeto del Observer. Conoce solo la interfaz SolicitudObserver, nunca a
 * correo, citas o pagos. Agregar un aviso mas = suscribir otro observador.
 */
public class Solicitud {

    private final String viajero;
    private final TipoVisa tipo;
    private final int edad;
    private final int meses;
    private EstadoSolicitud estado = EstadoSolicitud.ACTIVA;
    private final List<SolicitudObserver> observadores = new ArrayList<>();

    public Solicitud(String viajero, TipoVisa tipo, int edad, int meses) {
        this.viajero = viajero;
        this.tipo = tipo;
        this.edad = edad;
        this.meses = meses;
    }

    public void suscribir(SolicitudObserver observador) {
        observadores.add(observador);
    }

    public void cancelar() {
        if (estado == EstadoSolicitud.CANCELADA) {
            return;
        }
        estado = EstadoSolicitud.CANCELADA;
        notificarObservadores();
    }

    private void notificarObservadores() {
        for (SolicitudObserver observador : observadores) {
            observador.alCancelarse(this);
        }
    }

    public String getViajero() {
        return viajero;
    }

    public TipoVisa getTipo() {
        return tipo;
    }

    public int getEdad() {
        return edad;
    }

    public int getMeses() {
        return meses;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }
}
