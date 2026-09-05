package com.chainofresponsability.Handler;
import com.chainofresponsability.Problem.Problem;

public class DecanaturaHandler extends BaseHandler {
    public DecanaturaHandler(SubHandler subHandler) {
        super("Decanatura", 5, subHandler);
    }

    @Override
    public boolean canResolve(Problem problem) {
        // Decanatura es la última instancia de la cadena: siempre acepta
        // el problema, sin importar su nivel, para garantizar que ningún
        // caso quede sin resolver.
        this.problemaActual = problem;
        return true;
    }
}
