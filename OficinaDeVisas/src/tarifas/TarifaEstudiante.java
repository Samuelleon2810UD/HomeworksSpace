package tarifas;

/** Visa de estudio: 50% de descuento sobre el subtotal. */
public class TarifaEstudiante implements TarifaStrategy {

    private static final double FACTOR_DESCUENTO_ESTUDIANTE = 0.5;

    @Override
    public double ajustar(double subtotal) {
        return subtotal * FACTOR_DESCUENTO_ESTUDIANTE;
    }
}
