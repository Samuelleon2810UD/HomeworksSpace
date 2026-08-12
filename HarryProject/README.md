# Hogwarts: Duelo de Magos

Diseño e implementación en Java de un sistema de personajes y hechizos para un videojuego basado en la saga de Harry Potter. El proyecto está construido sobre el **patrón de diseño Strategy**, lo que permite que cada personaje cambie su hechizo hábil **en tiempo de ejecución**.

## Contexto del problema

En el juego participan distintos personajes (Harry Potter, Ron Weasley, Hermione Granger), cada uno hábil en un hechizo:

| Personaje         | Hechizo inicial     | Efecto                                                  |
|-------------------|----------------------|----------------------------------------------------------|
| Harry Potter      | Expecto Patronum     | Materializa un guardián mágico protector                |
| Ron Weasley       | Expelliarmus         | Desarma a la víctima, quitándole el objeto de las manos |
| Hermione Granger  | Wingardium Leviosa   | Hace levitar objetos                                     |

Requisito clave: un personaje puede **cambiar** el hechizo en el que es hábil (ej: Hermione a veces usa **Oculus Reparo** para arreglar los anteojos de Harry), sin dejar de ser el mismo objeto ni la misma clase.

## Patrón de diseño: Strategy

En vez de que cada personaje implemente su propio hechizo como método fijo, cada `Personaje` **tiene un** `Hechizo` (composición) que puede sustituirse dinámicamente:

- `Hechizo` es una **interfaz** con el contrato común (`lanzar()`, `getNombre()`).
- Cada hechizo concreto (`WingardiumLeviosa`, `OculusReparo`, `Expelliarmus`, `ExpectoPatronum`) implementa esa interfaz con su propio comportamiento.
- `Personaje` es una clase abstracta que guarda una referencia a un `Hechizo` y expone `aprenderHechizo(Hechizo nuevo)` para reemplazarlo en caliente.
- `HarryPotter`, `RonWeasley` y `HermioneGranger` solo definen su hechizo **inicial** en el constructor; el resto del comportamiento se hereda de `Personaje`.

Esto cumple el principio abierto/cerrado: agregar un hechizo nuevo (ej. `Alohomora`) implica solo crear una clase que implemente `Hechizo`, sin tocar ninguna clase de personaje existente.

## Diagrama de clases

Ver [`diagrama_clases.mermaid`](./diagrama_clases.mermaid). Puede visualizarse pegando su contenido en [mermaid.live](https://mermaid.live) o en cualquier editor/plataforma compatible con Mermaid (GitHub, Notion, VS Code, etc.).

Relaciones principales:
- `Hechizo <|.. HechizoConcreto` — implementación de interfaz.
- `Personaje <|-- PersonajeConcreto` — herencia.
- `Personaje "1" *-- "1" Hechizo` — composición: el personaje tiene un hechizo activo, intercambiable en tiempo de ejecución.

## Estructura del proyecto

```
src/
├── Hechizo.java              # Interfaz (estrategia)
├── WingardiumLeviosa.java    # Hechizo concreto
├── OculusReparo.java         # Hechizo concreto
├── Expelliarmus.java         # Hechizo concreto
├── ExpectoPatronum.java      # Hechizo concreto
├── Personaje.java            # Clase abstracta (contexto de la estrategia)
├── HarryPotter.java          # Personaje concreto
├── RonWeasley.java           # Personaje concreto
├── HermioneGranger.java      # Personaje concreto
└── Main.java                 # Menú interactivo de consola
diagrama_clases.mermaid       # Diagrama de clases UML
```

## Cómo compilar y ejecutar

Requiere JDK 11 o superior.

```bash
# Desde la carpeta src/
javac *.java -d ../out
java -cp ../out Main
```

## Uso del menú interactivo

Al ejecutar `Main`, se muestra un menú de consola:

```
----------------- MENÚ PRINCIPAL -----------------
1. Lanzar un hechizo con un personaje
2. Enseñar un nuevo hechizo a un personaje (cambio en tiempo de ejecución)
3. Ver estado actual de los personajes
0. Salir
```

- **Opción 1**: elige un personaje y un objetivo; se ejecuta su hechizo actual.
- **Opción 2**: elige un personaje y un hechizo de la lista; el personaje lo adopta como su nuevo hechizo hábil (demuestra el cambio en tiempo de ejecución vía `aprenderHechizo`).
- **Opción 3**: muestra el hechizo hábil actual de cada personaje.

### Ejemplo de flujo

```
1. Lanzar un hechizo con un personaje
> Harry Potter -> "un dementor"
=> ¡Expecto Patronum! Un guardián mágico aparece para proteger contra un dementor.

2. Enseñar un nuevo hechizo a un personaje
> Hermione Granger -> Oculus Reparo
=> Hermione Granger cambia su hechizo a: Oculus Reparo

3. Ver estado actual de los personajes
- Harry Potter -> hechizo hábil: Expecto Patronum
- Ron Weasley -> hechizo hábil: Expelliarmus
- Hermione Granger -> hechizo hábil: Oculus Reparo
```

## Posibles extensiones

- Agregar más hechizos (`Alohomora`, `Lumos`, `Petrificus Totalus`) creando nuevas clases que implementen `Hechizo`.
- Agregar más personajes extendiendo `Personaje`.
- Sustituir el menú de consola por una interfaz gráfica, reutilizando exactamente la misma lógica de dominio (`Personaje`, `Hechizo`).
