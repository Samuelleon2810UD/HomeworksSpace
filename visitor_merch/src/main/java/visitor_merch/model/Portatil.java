package visitor_merch.model;

import visitor_merch.visitor.Visitante;

public class Portatil implements Producto {
    private int price=400;
    private int weight=1000;

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
