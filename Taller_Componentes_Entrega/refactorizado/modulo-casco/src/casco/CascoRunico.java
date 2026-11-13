package casco;

public class CascoRunico implements ProteccionElemental {
    @Override
    public String resistirAtaque(String elemento) {
        if ("FISICO".equalsIgnoreCase(elemento)) {
            return "Las runas no detienen golpes fisicos";
        }
        if ("FUEGO".equalsIgnoreCase(elemento)
                || "HIELO".equalsIgnoreCase(elemento)
                || "RAYO".equalsIgnoreCase(elemento)
                || "MAGICO".equalsIgnoreCase(elemento)) {
            return "Ataque de " + elemento + " resistido por las runas";
        }
        return "Elemento desconocido: " + elemento;
    }
}
