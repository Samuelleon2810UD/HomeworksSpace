# El Alquimista de la Torre Carmesí — Taller Interpreter

Implementación en Java del patrón de diseño **Interpreter**, aplicada a un
lenguaje de comandos para un alquimista que se abre paso en una torre en
ruinas, enfrentando a un guardián mecánico (el **Constructo**) mientras la
torre misma se desestabiliza a su alrededor.

Esta versión busca ser deliberadamente más compleja que un intérprete básico:
las **condiciones del lenguaje son componibles** (con combinadores lógicos Y
/ O / NO), existe una **expresión de repetición** además de la secuencia, y
el Context tiene **cuatro recursos que interactúan entre sí** (vida del
Constructo, maná, ingredientes/pociones e inestabilidad de la torre), varios
de los cuales se modifican como efecto secundario de otras acciones.

## 1. El lenguaje

### Expresiones terminales

| Instrucción | Efecto sobre el Context |
|---|---|
| `RECOLECTAR` | +1 ingrediente |
| `DESTILAR` | Consume 2 ingredientes → +1 poción (falla sin efecto si no alcanza) |
| `INVOCAR_LLAMA` | Consume 15 maná → -35 vida del Constructo **y +25 inestabilidad** de la torre |
| `SELLAR_GRIETA` | Consume 10 maná → -30 inestabilidad de la torre |
| `MEDITAR` | +25 maná, pero +5 inestabilidad (el tiempo pasa, la torre sigue crujiendo) |
| `HUIR` | Acción narrativa de escape, sin costo |

### Expresiones compuestas

| Construcción | Significado |
|---|---|
| `A + B + ...` (`Secuencia`) | Ejecuta las instrucciones en orden |
| `REPETIR n VECES (expr)` (`Repetir`) | Ejecuta `expr` n veces sobre el mismo Context, cada vuelta viendo el estado que dejó la anterior |

### Expresión condicional y condiciones componibles

```
SI <condición> ENTONCES <expr> SINO <expr>
```

Lo distintivo de esta versión: la `<condición>` no es un único chequeo fijo,
sino un **árbol de condiciones** construido con combinadores lógicos que
implementan la misma interfaz `Condicion`:

| Condición | Significado |
|---|---|
| `CONSTRUCTO_ACTIVO` | ¿El Constructo sigue con vida? |
| `INESTABILIDAD_ALTA` | ¿Inestabilidad de la torre ≥ 70? |
| `MANA_SUFICIENTE` | ¿Maná actual ≥ 20? |
| `INGREDIENTES_SUFICIENTES` | ¿Ingredientes ≥ 3? |
| `Y(c1, c2)` | AND lógico de dos condiciones |
| `O(c1, c2)` | OR lógico de dos condiciones |
| `NO(c)` | Negación de una condición |

Esto permite condiciones tan expresivas como
`Y(CONSTRUCTO_ACTIVO, NO(INESTABILIDAD_ALTA))`, y el "SINO SI" (cadena de
else-if) se logra simplemente anidando otro `Condicional` dentro de la rama
`sino` — no hace falta ningún constructo especial para eso.

### El requisito de estado transformado

`INVOCAR_LLAMA` y `SELLAR_GRIETA` son opuestos que compiten por el mismo
recurso (`inestabilidadTorre`): uno la sube, el otro la baja. La condición
`INESTABILIDAD_ALTA` se evalúa siempre contra el valor **actual** de ese
campo. Por eso, en el **Caso de prueba 5** y en el **desafío final**,
`INVOCAR_LLAMA` empuja la inestabilidad por encima del umbral y el
condicional que sigue, en la misma expresión, reacciona a ese nuevo estado —
no al que había al principio.

## 2. Estructura del proyecto

```
AlquimistaTorreCarmesi/
└── src/main/java/com/taller/interpreter/
    ├── Main.java                          # casos de prueba, desafío final, menú interactivo
    ├── context/
    │   └── Context.java                   # vida del Constructo, maná, ingredientes, pociones, inestabilidad
    └── expression/
        ├── Expression.java                # interfaz raíz del patrón (interpret(Context))
        ├── Recolectar.java                # terminal
        ├── Destilar.java                  # terminal
        ├── InvocarLlama.java              # terminal (modifica 2 campos del Context a la vez)
        ├── SellarGrieta.java              # terminal (contrapeso de InvocarLlama)
        ├── Meditar.java                   # terminal
        ├── Huir.java                      # terminal
        ├── Secuencia.java                 # compuesta: A + B + ...
        ├── Repetir.java                   # compuesta: REPETIR n VECES (expr)
        ├── Condicional.java               # SI ... ENTONCES ... SINO ...
        └── condicion/
            ├── Condicion.java             # estrategia de evaluación (evaluar(Context))
            ├── ConstructoActivo.java
            ├── InestabilidadAlta.java
            ├── ManaSuficiente.java
            ├── IngredientesSuficientes.java
            ├── Y.java                     # combinador AND
            ├── O.java                     # combinador OR
            └── No.java                    # combinador NOT
```

### Terminales vs. no terminales, y qué viaja en el Context

- **Terminales**: `Recolectar`, `Destilar`, `InvocarLlama`, `SellarGrieta`,
  `Meditar`, `Huir`. Cada una es una acción atómica que no contiene otras
  expresiones.
- **No terminales**: `Secuencia` y `Repetir` (contienen y delegan en otras
  `Expression`), y `Condicional` (contiene una `Condicion` y dos ramas
  `Expression`). Las condiciones mismas tienen su propia jerarquía no
  terminal: `Y`, `O` y `No` contienen otras `Condicion` y pueden anidarse
  indefinidamente.
- **Context**: transporta `vidaConstructo`, `manaActual`, `ingredientes`,
  `pocionesPreparadas` e `inestabilidadTorre`. Viaja por referencia durante
  toda la interpretación. Ningún campo se lee "una sola vez": `InvocarLlama`
  modifica dos campos en la misma llamada, `SellarGrieta` revierte parte de
  ese efecto, y las condiciones siempre consultan el valor vigente.

### Por qué no hay if/else por tipo

Ninguna clase pregunta "¿qué tipo de expresión/condición es esta?".
`Secuencia` y `Repetir` solo llaman a `interpret()` sobre lo que contienen;
`Condicional` solo llama a `evaluar()` sobre su `Condicion`. Que esa
condición sea simple (`ConstructoActivo`) o un árbol de combinadores
(`Y(..., No(...))`) es indiferente para quien la usa — así se pueden agregar
nuevas instrucciones o condiciones sin modificar ninguna clase existente
(principio abierto/cerrado).

## 3. Cómo compilar y ejecutar

Requiere JDK 11 o superior.

```bash
find src -name "*.java" > sources.txt
javac -d out @sources.txt
java -cp out com.taller.interpreter.Main
```

Al ejecutar, el programa:

1. Corre automáticamente los **5 casos de prueba** de la Parte 6, con su
   salida visible en consola.
2. Corre el **desafío final** (Parte 4): la expresión con **3 niveles de
   anidación** y condiciones Y/O/NO, ejecutada con 3 contextos distintos
   (torre estable, torre inestable con maná suficiente, Constructo
   destruido) para mostrar que recorre la rama correcta en cada caso.
3. Abre un **menú interactivo** para ejecutar instrucciones sueltas,
   `REPETIR`, un condicional con combinador lógico, o el desafío completo,
   sobre un `Context` que el usuario puede reiniciar con sus propios valores.

## 4. Desafío final (Parte 4)

```
SI (CONSTRUCTO_ACTIVO Y NO INESTABILIDAD_ALTA) ENTONCES
    REPETIR 2 VECES (INVOCAR_LLAMA)
SINO SI (CONSTRUCTO_ACTIVO Y INESTABILIDAD_ALTA) ENTONCES
    SELLAR_GRIETA + (SI MANA_SUFICIENTE ENTONCES INVOCAR_LLAMA SINO MEDITAR)
SINO
    RECOLECTAR + RECOLECTAR + DESTILAR
```

Tres niveles de anidación: el `Condicional` externo tiene como rama `sino`
otro `Condicional` (que resuelve el "SINO SI"), y ese segundo `Condicional`
tiene, dentro de su rama `entonces` (una `Secuencia`), un **tercer**
`Condicional`. Además, la condición externa y la del segundo nivel son
árboles `Y(...)`/`No(...)`, no chequeos simples.

## 5. Casos de prueba (Parte 6)

| Caso | Expresión | Contexto inicial | Resultado esperado |
|---|---|---|---|
| 1 | `RECOLECTAR` | — | Recoge un ingrediente |
| 2 | `RECOLECTAR + RECOLECTAR + DESTILAR` | — | Recoge 2 ingredientes y prepara una poción |
| 3 | `SI CONSTRUCTO_ACTIVO ENTONCES INVOCAR_LLAMA SINO RECOLECTAR` | Constructo activo | Invoca llama |
| 4 | `SI CONSTRUCTO_ACTIVO ENTONCES INVOCAR_LLAMA SINO RECOLECTAR` | Constructo destruido | Recolecta |
| 5 | `INVOCAR_LLAMA` + `SI INESTABILIDAD_ALTA ENTONCES SELLAR_GRIETA SINO INVOCAR_LLAMA` | Inestabilidad inicial baja (50) | La condición evalúa el nuevo estado (75, tras el conjuro) y elige SELLAR_GRIETA |

Todos estos casos están implementados y se ejecutan automáticamente en
`Main.ejecutarCasosDePrueba()`.
