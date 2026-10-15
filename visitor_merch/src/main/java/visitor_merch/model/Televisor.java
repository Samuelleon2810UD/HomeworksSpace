package visitor_merch.model;

import visitor_merch.visitor.Visitante;

public class Televisor implements Producto {
    private int price=300;
    private int weight=4000;

    @Override
    public void accept(Visitante visitante) {
        visitante.visitar(this);
    }

    public double getPrice() {
        return this.price;
    }

    public double getWeight() {
        return this.weight;
    }
}
