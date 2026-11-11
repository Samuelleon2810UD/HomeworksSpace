package consulado;

import util.Consola;

/** Unica responsabilidad: cobrar la tarifa al viajero. */
public class CajaDePagos {

    private static final String ACTOR = "CajaDePagos";

    public void cobrar(String viajero, double monto) {
        Consola.registrar(ACTOR, "Cobrando $" + monto + " a " + viajero);
    }
}
