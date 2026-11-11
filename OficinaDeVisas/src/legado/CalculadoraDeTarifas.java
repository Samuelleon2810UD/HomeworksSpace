package legado;

/**
 * CODIGO HEREDADO (Fragmento 2) - ANTIPATRON: Copy-Paste Programming.
 * La misma formula (base * meses + 10) esta copiada tres veces.
 * Para poder mostrar "cuantos lugares hay que editar", la tarifa base de cada
 * metodo se guarda en su propio campo y cada edicion se cuenta.
 */
public class CalculadoraDeTarifas {

    private double baseTurista = 50;
    private double baseEstudiante = 50;
    private double baseTrabajo = 50;
    private int lugaresEditados = 0;

    public void subirTarifaBaseTurista(double nueva) {
        baseTurista = nueva;
        lugaresEditados++;
    }

    public void subirTarifaBaseEstudiante(double nueva) {
        baseEstudiante = nueva;
        lugaresEditados++;
    }

    public void subirTarifaBaseTrabajo(double nueva) {
        baseTrabajo = nueva;
        lugaresEditados++;
    }

    public int getLugaresEditados() {
        return lugaresEditados;
    }

    public double visaTurista(int meses) {
        double total = baseTurista * meses;
        total = total + 10;
        return total;
    }

    public double visaEstudiante(int meses) {
        double total = baseEstudiante * meses;
        total = total + 10;
        return total * 0.5;
    }

    public double visaTrabajo(int meses) {
        double total = baseTrabajo * meses;
        total = total + 10;
        return total * 1.5;
    }
}
