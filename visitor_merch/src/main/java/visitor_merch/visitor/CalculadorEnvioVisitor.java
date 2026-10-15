package visitor_merch.visitor;

import visitor_merch.model.Computador;
import visitor_merch.model.Portatil;
import visitor_merch.model.Tablet;
import visitor_merch.model.Televisor;

public class CalculadorEnvioVisitor implements Visitante {
    private double costoEnvio = 0;

    @Override
    public void visitar(Tablet tablet) {
        double envioPercent = (tablet.getWeight()/100)*2;
        this.costoEnvio += tablet.getPrice() * envioPercent / 100;
    }

    @Override
    public void visitar(Portatil portatil) {
        double envioPercent = (portatil.getWeight()/100)*2;
        this.costoEnvio += portatil.getPrice() * envioPercent / 100;
    }

    @Override
    public void visitar(Computador computador) {
        double envioPercent = (computador.getWeight()/100)*2;
        this.costoEnvio += computador.getPrice() * envioPercent / 100;
    }

    @Override
    public void visitar(Televisor televisor) {
        double envioPercent = (televisor.getWeight()/100)*2;
        this.costoEnvio += televisor.getPrice() * envioPercent / 100;
    }

    public double getCostoEnvio() {
        return costoEnvio;
    }
    
}
