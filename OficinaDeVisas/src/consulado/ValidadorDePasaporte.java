package consulado;

import util.Consola;

/** Unica responsabilidad: validar que el pasaporte sea aceptable. */
public class ValidadorDePasaporte {

    public static final int LONGITUD_MINIMA = 6;
    private static final String ACTOR = "ValidadorDePasaporte";

    public boolean esValido(String pasaporte) {
        boolean valido = pasaporte != null && pasaporte.length() >= LONGITUD_MINIMA;
        Consola.registrar(ACTOR, valido ? "Pasaporte valido" : "Pasaporte invalido");
        return valido;
    }
}
