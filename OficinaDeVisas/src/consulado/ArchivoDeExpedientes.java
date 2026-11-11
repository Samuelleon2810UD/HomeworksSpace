package consulado;

import util.Consola;

/** Unica responsabilidad: guardar el expediente del viajero. */
public class ArchivoDeExpedientes {

    private static final String ACTOR = "ArchivoDeExpedientes";

    public void guardar(String viajero) {
        Consola.registrar(ACTOR, "Guardando expediente de " + viajero);
    }
}
