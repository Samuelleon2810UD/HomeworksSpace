package com.tallerbuild.Parte4;
// Parte 4 — Corrección del error de reutilización del Builder
//
// Problema original: el ComputadorBuilder creaba UNA sola instancia de Computador
// al inicializarse, y build() devolvía esa misma referencia. Si el builder se
// reutilizaba para armar un segundo computador, se seguían mutando los campos
// del MISMO objeto, afectando también al "computador" que ya se había entregado.
//
// Solución aplicada:
// 1) Computador pasa a ser inmutable (campos finales, fijados solo en el constructor).
// 2) El builder acumula el estado parcial en variables propias (no en el Product).
// 3) build() construye una instancia NUEVA de Computador cada vez.
// 4) Opcionalmente, reset() permite reiniciar el builder explícitamente para reutilizarlo.


public class ComputadorBuilderCorrect {

    // ---------- Product inmutable ----------
    public static final class Computador {
        private final String procesador;
        private final String ram;
        private final String almacenamiento;

        private Computador(ComputadorBuilder builder) {
            this.procesador = builder.procesador;
            this.ram = builder.ram;
            this.almacenamiento = builder.almacenamiento;
        }

        @Override
        public String toString() {
            return "Computador{procesador='" + procesador + "', ram='" + ram
                    + "', almacenamiento='" + almacenamiento + "'}";
        }
    }

    // ---------- Builder ----------
    public static class ComputadorBuilder {
        private String procesador;
        private String ram;
        private String almacenamiento;

        public ComputadorBuilder procesador(String p) {
            this.procesador = p;
            return this;
        }

        public ComputadorBuilder ram(String r) {
            this.ram = r;
            return this;
        }

        public ComputadorBuilder almacenamiento(String a) {
            this.almacenamiento = a;
            return this;
        }

        public Computador build() {
            return new Computador(this);
        }


        public ComputadorBuilder reset() {
            this.procesador = null;
            this.ram = null;
            this.almacenamiento = null;
            return this;
        }
    }

    // ---------- Demostración del arreglo ----------
    public static void main(String[] args) {
        ComputadorBuilder builder = new ComputadorBuilder();

        Computador pcOficina = builder
                .procesador("Intel i5")
                .ram("8GB")
                .almacenamiento("512GB SSD")
                .build();

        Computador pcGamer = builder
                .reset()
                .procesador("AMD Ryzen 9")
                .ram("32GB")
                .almacenamiento("2TB NVMe")
                .build();

        System.out.println(pcOficina);
        System.out.println(pcGamer);
    }
}
