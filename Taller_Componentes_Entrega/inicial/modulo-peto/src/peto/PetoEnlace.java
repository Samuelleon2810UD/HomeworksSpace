package peto;

import botas.BotasEnlace;

public class PetoEnlace {
    public boolean escudoActivo(BotasEnlace botas) {
        return botas != null && botas.tieneEnergia();
    }
}
