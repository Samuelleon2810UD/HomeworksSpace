package legado;

/**
 * CODIGO HEREDADO (Fragmento 4) - ANTIPATRON: Acoplamiento fuerte.
 * La solicitud CREA a los tres servicios y los llama a mano, uno por uno.
 * Un viernes alguien olvido la llamada a pagos y el reembolso se perdio
 * (la linea "pagos.reembolsar()" nunca se escribio).
 */
public class SolicitudAcoplada {

    private String estado = "ACTIVA";
    private CorreoServicio correo = new CorreoServicio();
    private CitasServicio citas = new CitasServicio();
    private PagosServicio pagos = new PagosServicio();

    public void cancelar() {
        estado = "CANCELADA";
        correo.avisarCancelacion();
        citas.liberarCupo();
        // OLVIDADO: pagos.reembolsar();
    }

    public String getEstado() {
        return estado;
    }

    public boolean reembolsoFueProcesado() {
        return pagos.isReembolsoProcesado();
    }
}
