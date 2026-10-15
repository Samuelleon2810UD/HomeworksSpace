package visitor_merch.visitor;

import visitor_merch.model.Computador;
import visitor_merch.model.Portatil;
import visitor_merch.model.Tablet;
import visitor_merch.model.Televisor;

public class CalculadorSubtotalVisitor implements Visitante {
    private double subtotal = 0;

    @Override
    public void visitar(Tablet tablet) {
        subtotal += tablet.getPrice();
    }

    @Override
    public void visitar(Portatil portatil) {
        subtotal += portatil.getPrice();
    }

    @Override
    public void visitar(Computador computador) {
        subtotal += computador.getPrice();
    }

    @Override
    public void visitar(Televisor televisor) {
        subtotal += televisor.getPrice();
    }

    public double getSubtotal() {
        return subtotal;
    }
}
