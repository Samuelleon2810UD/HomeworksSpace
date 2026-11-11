package tarifas;

/** Visa de turismo: se cobra el subtotal sin ajuste. */
public class TarifaTurista implements TarifaStrategy {

    private static final double FACTOR_TURISTA = 1.0;

    @Override
    public double ajustar(double subtotal) {
        return subtotal * FACTOR_TURISTA;
    }
}
