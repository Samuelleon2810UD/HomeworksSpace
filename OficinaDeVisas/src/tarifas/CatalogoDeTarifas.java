package tarifas;

import visas.TipoVisa;

/** Unico lugar donde se asocia cada TipoVisa con su estrategia de tarifa. */
public final class CatalogoDeTarifas {

    private CatalogoDeTarifas() {
    }

    public static CalculadoraDeTarifas crear(TarifaBase base) {
        CalculadoraDeTarifas calculadora = new CalculadoraDeTarifas(base);
        calculadora.registrar(TipoVisa.TURISTA, new TarifaTurista());
        calculadora.registrar(TipoVisa.ESTUDIANTE, new TarifaEstudiante());
        calculadora.registrar(TipoVisa.TRABAJO, new TarifaTrabajo());
        return calculadora;
    }
}
