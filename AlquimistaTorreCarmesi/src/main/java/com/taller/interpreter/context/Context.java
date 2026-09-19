package com.taller.interpreter.context;

/**
 * Contexto compartido durante la interpretación del lenguaje de la Torre Carmesí.
 * <p>
 * A diferencia de un simple "dato de entrada", este objeto viaja y se
 * transforma constantemente: INVOCAR_LLAMA daña al Constructo pero también
 * desestabiliza la torre; SELLAR_GRIETA repara esa inestabilidad pero cuesta
 * maná; DESTILAR consume ingredientes para producir pociones. Las
 * condiciones del lenguaje (Y, O, NO, umbrales) siempre se evalúan contra
 * este estado en el instante en que se ejecutan, nunca contra una copia
 * inicial.
 */
public class Context {

    private int vidaConstructo;
    private int manaActual;
    private int ingredientes;
    private int pocionesPreparadas;
    private int inestabilidadTorre; // 0 (estable) - 100 (colapso inminente)

    private final int umbralInestabilidadAlta;
    private final int umbralManaSuficiente;
    private final int umbralIngredientesSuficientes;

    public Context(int vidaConstructo, int manaActual, int ingredientes, int inestabilidadTorre) {
        this.vidaConstructo = vidaConstructo;
        this.manaActual = manaActual;
        this.ingredientes = ingredientes;
        this.pocionesPreparadas = 0;
        this.inestabilidadTorre = clamp(inestabilidadTorre, 0, 100);
        this.umbralInestabilidadAlta = 70;
        this.umbralManaSuficiente = 20;
        this.umbralIngredientesSuficientes = 3;
    }

    private static int clamp(int valor, int min, int max) {
        return Math.max(min, Math.min(max, valor));
    }

    // --- Constructo (el guardián mecánico de la torre) ---

    public boolean isConstructoActivo() {
        return vidaConstructo > 0;
    }

    public int getVidaConstructo() {
        return vidaConstructo;
    }

    /** INVOCAR_LLAMA: daña al Constructo y, como efecto secundario, desestabiliza la torre. */
    public void danarConstructo(int danio, int aumentoInestabilidad) {
        this.vidaConstructo = Math.max(0, this.vidaConstructo - danio);
        this.inestabilidadTorre = clamp(this.inestabilidadTorre + aumentoInestabilidad, 0, 100);
    }

    // --- Maná ---

    public int getManaActual() {
        return manaActual;
    }

    public boolean consumirMana(int cantidad) {
        if (this.manaActual < cantidad) {
            return false;
        }
        this.manaActual -= cantidad;
        return true;
    }

    public void recuperarMana(int cantidad) {
        this.manaActual += cantidad;
    }

    // --- Ingredientes y pociones ---

    public int getIngredientes() {
        return ingredientes;
    }

    public void recolectarIngrediente() {
        this.ingredientes += 1;
    }

    public boolean consumirIngredientes(int cantidad) {
        if (this.ingredientes < cantidad) {
            return false;
        }
        this.ingredientes -= cantidad;
        return true;
    }

    public int getPocionesPreparadas() {
        return pocionesPreparadas;
    }

    public void agregarPocion() {
        this.pocionesPreparadas += 1;
    }

    // --- Inestabilidad de la torre ---

    public int getInestabilidadTorre() {
        return inestabilidadTorre;
    }

    public void reducirInestabilidad(int cantidad) {
        this.inestabilidadTorre = clamp(this.inestabilidadTorre - cantidad, 0, 100);
    }

    public void aumentarInestabilidad(int cantidad) {
        this.inestabilidadTorre = clamp(this.inestabilidadTorre + cantidad, 0, 100);
    }

    // --- Umbrales usados por las condiciones ---

    public int getUmbralInestabilidadAlta() {
        return umbralInestabilidadAlta;
    }

    public int getUmbralManaSuficiente() {
        return umbralManaSuficiente;
    }

    public int getUmbralIngredientesSuficientes() {
        return umbralIngredientesSuficientes;
    }

    @Override
    public String toString() {
        return String.format(
                "Context[vidaConstructo=%d, mana=%d, ingredientes=%d, pociones=%d, inestabilidad=%d]",
                vidaConstructo, manaActual, ingredientes, pocionesPreparadas, inestabilidadTorre);
    }
}
