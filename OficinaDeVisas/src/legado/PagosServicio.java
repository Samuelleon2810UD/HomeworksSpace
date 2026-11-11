package legado;

/** Caja de pagos del sistema heredado. */
public class PagosServicio {

    private boolean reembolsoProcesado = false;

    public void reembolsar() {
        reembolsoProcesado = true;
        System.out.println("   [PagosServicio] Reembolso procesado");
    }

    public boolean isReembolsoProcesado() {
        return reembolsoProcesado;
    }
}
