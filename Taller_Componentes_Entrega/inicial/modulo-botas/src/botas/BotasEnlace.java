package botas;

import peto.PetoEnlace;

public class BotasEnlace {
    public boolean tieneEnergia() {
        return true;
    }

    public boolean sincronizar(PetoEnlace peto) {
        return peto != null;
    }
}
