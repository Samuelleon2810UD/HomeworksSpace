package visitor_merch.model;

import visitor_merch.visitor.Visitante;

public class Tablet implements Producto {
    private int price=200;
    private int weight=250;

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
