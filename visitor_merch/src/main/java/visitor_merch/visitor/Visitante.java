package visitor_merch.visitor;

import visitor_merch.model.Computador;
import visitor_merch.model.Portatil;
import visitor_merch.model.Tablet;
import visitor_merch.model.Televisor;

public interface Visitante {
    void visitar(Tablet tablet);
    void visitar(Portatil portatil);
    void visitar(Computador computador);
    void visitar(Televisor televisor);
}
