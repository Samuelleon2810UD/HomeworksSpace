package visitor_merch.model;

import java.util.LinkedHashMap;
import java.util.Map;
import visitor_merch.visitor.Visitante;

public class Pedido {

	private final Map<Producto, Integer> productos = new LinkedHashMap<>();

	public void agregarProducto(Producto producto, int cantidad) {
		if (producto == null) {
			throw new IllegalArgumentException("El producto no puede ser null");
		}
		if (cantidad <= 0) {
			throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
		}
		productos.merge(producto, cantidad, Integer::sum);
	}

	public void accept(Visitante visitante) {
		for (Map.Entry<Producto, Integer> linea : productos.entrySet()) {
			for (int unidad = 0; unidad < linea.getValue(); unidad++) {
				linea.getKey().accept(visitante);
			}
		}
	}
}
