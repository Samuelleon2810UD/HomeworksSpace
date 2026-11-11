package legado;

/**
 * CODIGO HEREDADO (Fragmento 1) - ANTIPATRON: God Object.
 * Ramiro valida, cobra, guarda y envia el correo. Todo en una sola clase.
 * (Solo se agrego el prefijo [OficinaDeTodo] a los mensajes para ver quien actua.)
 */
public class OficinaDeTodo {

    public boolean procesar(String nombre, String pasaporte, double precio) {
        if (pasaporte == null || pasaporte.length() < 6) {
            System.out.println("   [OficinaDeTodo] Pasaporte invalido");
            return false;
        }
        System.out.println("   [OficinaDeTodo] Cobrando $" + precio + " a " + nombre);
        System.out.println("   [OficinaDeTodo] Guardando expediente de " + nombre);
        System.out.println("   [OficinaDeTodo] Correo para " + nombre + ": su visa va en camino");
        return true;
    }
}
