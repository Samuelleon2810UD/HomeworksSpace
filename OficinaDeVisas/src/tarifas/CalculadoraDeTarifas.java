package tarifas;

import java.util.EnumMap;
import java.util.Map;
import visas.TipoVisa;

/**
 * Contexto del Strategy: calcula el precio de cualquier tipo de visa con UNA sola formula.
 * No contiene ifs por tipo; la estrategia correcta se busca en un mapa.
 */
public class CalculadoraDeTarifas {

    private final TarifaBase base;
    private final Map<TipoVisa, TarifaStrategy> estrategias = new EnumMap<>(TipoVisa.class);

    public CalculadoraDeTarifas(TarifaBase base) {
        this.base = base;
    }

    public void registrar(TipoVisa tipo, TarifaStrategy estrategia) {
        estrategias.put(tipo, estrategia);
    }

    public double calcular(TipoVisa tipo, int meses) {
        TarifaStrategy estrategia = estrategias.get(tipo);
        if (estrategia == null) {
            throw new IllegalStateException("No hay tarifa registrada para " + tipo);
        }
        return estrategia.ajustar(base.subtotal(meses));
    }
}
