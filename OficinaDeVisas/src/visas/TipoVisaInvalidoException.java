package visas;

/** Se lanza cuando un codigo no corresponde a ningun TipoVisa. */
public class TipoVisaInvalidoException extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    public TipoVisaInvalidoException(String mensaje) {
        super(mensaje);
    }
}
