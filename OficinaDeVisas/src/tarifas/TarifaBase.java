package tarifas;

/**
 * UNICO punto de cambio del precio base.
 * Calcula una sola vez el subtotal (precio por mes * meses + cargo fijo).
 */
public class TarifaBase {

    public static final double CARGO_FIJO = 10;

    private double precioPorMes;
    private int cambiosRealizados = 0;

    public TarifaBase(double precioPorMes) {
        this.precioPorMes = precioPorMes;
    }

    public void cambiarPrecioPorMes(double nuevoPrecio) {
        this.precioPorMes = nuevoPrecio;
        cambiosRealizados++;
    }

    public int getCambiosRealizados() {
        return cambiosRealizados;
    }

    public double subtotal(int meses) {
        return precioPorMes * meses + CARGO_FIJO;
    }
}
