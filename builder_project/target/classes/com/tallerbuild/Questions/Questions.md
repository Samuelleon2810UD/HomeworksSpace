# Taller: Patrón de diseño Builder — Respuestas escritas

## Parte 1 — Preguntas conceptuales

**1. ¿Qué problema resuelve el patrón Builder? Mencione al menos dos desventajas del "constructor telescópico".**

El patrón Builder resuelve el problema de construir objetos complejos que tienen muchos parámetros, especialmente cuando varios de ellos son opcionales. En lugar de exponer un único constructor con todos los campos posibles, el Builder permite armar el objeto paso a paso, indicando solo los valores que realmente interesan, y separa el proceso de construcción de la representación final del objeto.

El "constructor telescópico" es la práctica de ofrecer varias sobrecargas de un mismo constructor, agregando parámetros progresivamente (uno con 2 parámetros, otro con 4, otro con 7, etc.). Sus desventajas principales son:

- **Poca legibilidad:** al llamar `new Pizza("delgada", "mediana", true, false, true, false, "bbq")` no es evidente qué representa cada valor booleano o cadena sin ir a revisar la firma del constructor.
- **Combinaciones difíciles de manejar:** si hay muchos campos opcionales, se necesitan demasiadas sobrecargas para cubrir todas las combinaciones útiles, o se obliga al programador a pasar `null`/`false` en parámetros que no le interesan, lo cual es propenso a errores (por ejemplo, invertir por accidente el orden de dos parámetros del mismo tipo).

**2. Nombre los 4 roles clásicos del patrón y explique en una frase qué hace cada uno.**

- **Product:** es el objeto complejo final que se está construyendo (por ejemplo, `Carro` o `Pizza`).
- **Builder:** es una interfaz (o clase abstracta) que declara los métodos para construir las distintas partes del Product, sin importar cómo se ensamblan internamente.
- **ConcreteBuilder:** implementa la interfaz Builder, guarda el estado parcial del objeto mientras se construye y ofrece el método para entregar el resultado final (por ejemplo `build()` o `getResultado()`).
- **Director:** conoce el orden y la combinación de pasos necesarios para construir una representación particular del producto, delegando el trabajo real de construcción al Builder que recibe.

**3. ¿Por qué se suele omitir el rol Director?**

Porque en muchas implementaciones modernas (especialmente con encadenamiento de métodos / *method chaining*) el propio código cliente decide en qué orden llamar a los métodos del Builder, y esas combinaciones suelen ser simples o muy variables como para justificar una clase aparte. El Director solo aporta valor real cuando existen "recetas" de construcción fijas y reutilizables (por ejemplo, plantillas predefinidas); si cada llamada configura el objeto de forma distinta, mantener un Director agrega una capa de indirección innecesaria.

**4. ¿Cuál es la diferencia entre la construcción y la representación final del objeto en el patrón Builder?**

La **construcción** es el proceso: la secuencia de pasos y llamadas al Builder que van definiendo cada parte del objeto (por ejemplo, ir agregando motor, color, habilidades, etc.). La **representación final** es el objeto Product ya completo y, normalmente, inmutable, que resulta de invocar el método de finalización (`build()`, `getResultado()`). El mismo proceso de construcción (mismo Builder, mismos pasos) puede generar distintas representaciones si cambian los valores usados en cada paso, y un mismo Director puede producir representaciones distintas según qué ConcreteBuilder reciba.

---

## Parte 3 — Identificar los roles

| Clase | Rol |
|---|---|
| a) `interface CarroBuilder { void ponerMotor(String m); void ponerColor(String c); Carro getResultado(); }` | **Builder** (interfaz que declara los pasos de construcción) |
| b) `class CarroDeportivoBuilder implements CarroBuilder { ... }` | **ConcreteBuilder** (implementación concreta que arma un tipo específico de Carro) |
| c) `class CarroDirector { void construirEdicionLimitada() { ... } }` | **Director** (conoce y orquesta la secuencia de pasos para una configuración específica) |
| d) `class Carro { private final String motor; private final String color; }` | **Product** (el objeto final que se está construyendo) |

---
# Parte 4 — Encontrar el error

## ¿Cuál es el problema?

El `ComputadorBuilder` crea **una sola instancia** de `Computador` en el momento en que se crea el builder:

```java
private Computador computador = new Computador();
```

Si el mismo builder se **reutiliza** para construir dos computadores distintos (llamando `build()` una vez, y luego siguiendo usando el mismo builder con nuevas llamadas a `procesador(...)` para "armar" un segundo computador), ambas variables terminan apuntando al **mismo objeto** en memoria, porque `build()` no crea una copia: simplemente devuelve la referencia interna.

Esto significa que:
- El primer `Computador` que creíamos ya "terminado" en realidad sigue siendo mutable y visible desde el builder.
- Al seguir llamando métodos del builder para configurar el segundo computador, esos cambios **también se reflejan en el primer objeto ya entregado**, porque es literalmente el mismo objeto.

En otras palabras: no hay reinicio de estado entre construcciones, y el producto no es inmutable, por lo que el builder termina "contaminando" un resultado que el cliente ya consideraba definitivo.

## ¿Cómo se arregla?

Hay dos correcciones complementarias:

1. **Reiniciar el estado interno después de cada `build()`**, creando un nuevo `Computador` para la siguiente construcción, en lugar de seguir reutilizando el mismo.
2. **Hacer que `Computador` sea inmutable**, construyéndolo con sus valores finales (por constructor) solo en el momento de `build()`, en vez de ir mutando campos públicos del objeto poco a poco. Así, aunque el builder se reutilice, el objeto ya entregado nunca puede cambiar después de haber sido construido.

Ver el código corregido en `ComputadorBuilderCorregido.java`.

---

## Parte 5 — Para argumentar

**¿Cuándo es favorable aplicar el patrón Builder?**

1. **Objetos con muchos parámetros opcionales:** por ejemplo, un `Personaje` de RPG (nombre y raza obligatorios, pero arma, armadura, mascota y habilidades opcionales) o una solicitud HTTP configurable (URL obligatoria, pero headers, timeout, cuerpo, etc. opcionales). El Builder evita constructores telescópicos y hace explícito qué campo se está fijando.
2. **Objetos que deben quedar inmutables tras su creación:** por ejemplo, un `Pedido` de una tienda en línea o un objeto de configuración (`ConexionBD`) que no debe cambiar una vez armado. El Builder permite ir acumulando datos en un objeto mutable temporal y entregar al final un Product inmutable y consistente.

**¿Cuándo NO conviene aplicar el patrón Builder?**

1. **Objetos simples con pocos atributos obligatorios:** por ejemplo, un `Punto(x, y)` o un `Color(r, g, b)`. Agregar un Builder aquí solo añade clases y complejidad sin resolver ningún problema real, ya que un constructor normal es perfectamente legible.
2. **Objetos que se crean una sola vez de forma fija y no varían su configuración:** por ejemplo, un `Singleton` de configuración leído directamente de un archivo, o un DTO que simplemente refleja una fila de base de datos con todos sus campos siempre presentes. En estos casos no hay combinaciones opcionales que justificar, y un constructor o incluso una fábrica simple es suficiente.
