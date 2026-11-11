package tarifas;

/** Strategy: la unica parte que cambia entre tipos de visa es como se ajusta el subtotal. */
public interface TarifaStrategy {

    double ajustar(double subtotal);
}
