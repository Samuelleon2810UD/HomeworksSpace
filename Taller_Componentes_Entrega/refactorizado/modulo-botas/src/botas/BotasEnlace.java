package botas;

import core.GemaEnergia;

public class BotasEnlace implements GemaEnergia {
    @Override
    public boolean tieneCarga() {
        return true;
    }

    public boolean sincronizar(GemaEnergia otra) {
        return otra != null && otra.tieneCarga();
    }
}
