package tarifas;

/** Visa de trabajo: recargo del 50% sobre el subtotal. */
public class TarifaTrabajo implements TarifaStrategy {

    private static final double FACTOR_RECARGO_TRABAJO = 1.5;

    @Override
    public double ajustar(double subtotal) {
        return subtotal * FACTOR_RECARGO_TRABAJO;
    }
}
