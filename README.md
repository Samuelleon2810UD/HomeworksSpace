# CoffeeCooperative

Proyecto Java (NetBeans) que aplica el patrón de diseño **Observer** para modelar
una cooperativa de café que notifica a sus baristas cada vez que se agrega un
nuevo lote de café.

## Patrón aplicado: Observer

- **Subject (sujeto observado):** `Subject.CoffeeCooperative` — mantiene la
  lista de lotes de café y la lista de baristas suscritos.
- **Observer (observador):** `Observer.Barist` — representa a un barista que
  quiere enterarse cuando hay lotes nuevos.
- **Interfaces:** `Subject.Subject` y `Observer.ObserverCooperative` definen
  el contrato entre ambos lados, siguiendo el principio abierto/cerrado: se
  pueden agregar nuevos tipos de observadores sin modificar el Subject.

## Modalidades de notificación

Cada barista se suscribe eligiendo **una** de estas tres modalidades:

| Modalidad          | ¿Qué recibe el barista?                                                |
|---------------------|--------------------------------------------------------------------------|
| Push completo        | La lista completa y actualizada de todos los lotes registrados.          |
| Push incremental      | Únicamente el lote que se acaba de agregar.                              |
| Pull                  | Solo un aviso de que hubo un cambio; el propio barista consulta (`getLotes()`) al Subject para obtener los datos que necesita. |

Esto se modela con el enum `Observer.ModalidadNotificacion` y con tres
sobrecargas del método `update()` en `ObserverCooperative`. Cuando se agrega
un lote, `CoffeeCooperative.InformBarist()` recorre a los baristas suscritos
y llama a la sobrecarga correspondiente según la modalidad de cada uno.

## Estructura del proyecto

```
Extras/
  Lotes.java                  -> datos de un lote de café (varietal, origen, altitud, etc.)
Observer/
  ObserverCooperative.java    -> interfaz del observador (3 firmas de update)
  ModalidadNotificacion.java  -> enum con las 3 modalidades
  Barist.java                 -> observador concreto (barista)
Subject/
  Subject.java                 -> interfaz del sujeto observado
  CoffeeCooperative.java        -> sujeto concreto (cooperativa)
coffeecooperative/
  CoffeeCooperative.java        -> clase con el main() y el menú de consola
```

> **Nota:** existen dos clases llamadas `CoffeeCooperative` en paquetes
> distintos (`coffeecooperative.CoffeeCooperative`, el `main`, y
> `Subject.CoffeeCooperative`, la implementación del patrón). Java lo permite
> porque están en paquetes diferentes, pero conviene tenerlo presente al leer
> el código para no confundirlas.

## Menú de consola

Al ejecutar el proyecto (`main.class = coffeecooperative.CoffeeCooperative`)
se muestra:

1. **Registrar barista** — pide nombre, ID y modalidad de notificación.
2. **Eliminar barista** — desuscribe a un barista de la lista.
3. **Agregar nuevo lote de café** — pide los datos del lote, lo agrega y
   dispara `InformBarist()`, notificando a cada barista según su modalidad.
4. **Listar baristas suscritos** — muestra cada barista, su modalidad y los
   lotes que conoce hasta el momento.
5. **Listar todos los lotes registrados** — muestra todos los lotes de la
   cooperativa.
0. **Salir**

## Compilación y ejecución manual (fuera de NetBeans)

```bash
javac -d out $(find . -name "*.java")
java -cp out coffeecooperative.CoffeeCooperative
```

## Correcciones realizadas sobre el código original

- Las listas `l` (lotes) y `o` (baristas) en `Subject.CoffeeCooperative` no
  estaban inicializadas, lo que causaba `NullPointerException` al primer uso.
  Se inicializan ahora en la declaración.
- `Lotes.java` no tenía getters ni `toString()`, así que era imposible leer o
  mostrar sus datos fuera de la clase. Se agregaron.
- Se agregó `getLotes()` a la interfaz `Subject`, necesario para que la
  modalidad Pull pueda consultar los datos.
- Se amplió `ObserverCooperative` de una sola firma `update(List<Lotes>)` a
  tres firmas, una por modalidad.
- Se resolvió un conflicto de nombres en el `main`: al importar la interfaz
  `Subject.Subject`, el nombre simple `Subject` dejaba de referirse al
  paquete y el compilador no encontraba `Subject.CoffeeCooperative`. Se quitó
  el import innecesario en esa clase.

Todo lo anterior fue compilado y ejecutado (con datos de prueba) para
confirmar que el programa funciona de extremo a extremo.
