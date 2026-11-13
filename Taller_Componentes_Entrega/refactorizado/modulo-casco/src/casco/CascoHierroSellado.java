package casco;

public class CascoHierroSellado implements ProteccionElemental {
    @Override
    public String resistirAtaque(String elemento) {
        if ("FISICO".equalsIgnoreCase(elemento)) {
            return "Golpe fisico bloqueado";
        }
        return "CASCO DESTRUIDO: No soporta ataques de " + elemento;
    }
}
