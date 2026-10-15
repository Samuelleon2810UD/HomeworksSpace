package visitor_merch.model;

import visitor_merch.visitor.Visitante;

public interface Producto {
    public void accept(Visitante visitante);
}
