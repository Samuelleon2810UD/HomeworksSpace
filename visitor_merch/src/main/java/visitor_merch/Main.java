package visitor_merch;

import visitor_merch.model.Computador;
import visitor_merch.model.Pedido;
import visitor_merch.model.Portatil;
import visitor_merch.model.Tablet;
import visitor_merch.model.Televisor;
import visitor_merch.visitor.CalculadorEnvioVisitor;
import visitor_merch.visitor.CalculadorSubtotalVisitor;

public class Main {
    public static void main(String[] args) {
        Pedido pedido = new Pedido();
        pedido.agregarProducto(new Tablet(), 2);
        pedido.agregarProducto(new Portatil(), 1);
        pedido.agregarProducto(new Computador(), 1);
        pedido.agregarProducto(new Televisor(), 1);

        CalculadorSubtotalVisitor calculadorSubtotal = new CalculadorSubtotalVisitor();
        pedido.accept(calculadorSubtotal);

        CalculadorEnvioVisitor calculadorEnvio = new CalculadorEnvioVisitor();
        pedido.accept(calculadorEnvio);

        double subtotal = calculadorSubtotal.getSubtotal();
        double costoEnvio = calculadorEnvio.getCostoEnvio();

        System.out.println("Resumen del pedido");
        System.out.printf("Subtotal:     $%.2f%n", subtotal);
        System.out.printf("Costo envío:  $%.2f%n", costoEnvio);
        System.out.printf("Total a pagar: $%.2f%n", subtotal + costoEnvio);
    }
}