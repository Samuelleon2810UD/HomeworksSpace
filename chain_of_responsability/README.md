# Chain of Responsibility – Gestión de Problemas Académicos

Implementación en Java del patrón de diseño **Chain of Responsibility**,
basada en el diagrama de clases `ChainOfResponsabilitydrawio.drawio`.
Modela el flujo de escalamiento de un problema reportado por un
estudiante, que va pasando por distintos niveles de autoridad
(Monitor → Profesor → Coordinador → Secretaria → Decanatura) hasta
encontrar quién puede resolverlo.

## Estructura del proyecto

```
chain-of-responsibility/
├── Problem.java            Interfaz: contrato de todo problema
├── RegularProblem.java     Problema concreto (descripción, nivel, solución)
├── Handler.java            Interfaz: contrato de todo handler
├── BaseHandler.java        Clase abstracta con la lógica común de los handlers
├── MonitorHandler.java     Eslabón nivel 1
├── ProfesorHandler.java    Eslabón nivel 2
├── CoordinadorHandler.java Eslabón nivel 3
├── SecretariaHandler.java  Eslabón nivel 4
├── DecanaturaHandler.java  Eslabón nivel 5 (última instancia)
├── SubHandler.java         Persona real que atiende el problema asignado
├── PrincipalHandler.java   Orquestador: recorre la cadena y delega
└── Main.java               Programa de consola para probar todo el flujo
```

## Mapeo con el diagrama de clases

| Elemento del diagrama | Clase/interfaz en el código | Notas |
|---|---|---|
| `<<interface>> Problem` | `Problem.java` | Se agregaron `getDescripcion()` y `getLevel()`, no presentes en el diagrama, para evitar castear a `RegularProblem` desde el resto del código. |
| `RegularProblem` | `RegularProblem.java` | Atributos y método tal como en el diagrama, más `resolver(String)` para registrar la solución. |
| `<<interface>> Handler` | `Handler.java` | `canResolve(Problem):boolean`, `delegate():void`. |
| `MonitorHandler`, `ProfesorHandler`, `CoordinadorHandler`, `SecretariaHandler`, `DecanaturaHandler` | Clases homónimas | Cada una implementa `Handler` a través de `BaseHandler` (ver más abajo). |
| `SubHandler` | `SubHandler.java` | Persona asignada (`name`, `level`, `isOcuped`, `problemAssigment`) tal como en el diagrama. |
| `PrincipalHandler` | `PrincipalHandler.java` | `problemsList`, `handlersList`, `selectedProblem`, `selectedHandler`, `problemState`, y los métodos `canResolve()`, `delegate()`, `getSate()`. |

### Decisiones de diseño no explícitas en el diagrama

- **`BaseHandler` (clase abstracta):** el diagrama no la incluye, pero se
  introdujo para no repetir la misma lógica de `canResolve()` /
  `delegate()` en las cinco clases concretas (principio DRY). El
  contrato público sigue siendo exactamente el de la interfaz `Handler`.
- **`PrincipalHandler` no implementa formalmente `Handler`:** en el
  diagrama tiene una flecha de implementación hacia `Handler`, pero sus
  métodos `canResolve()` y `delegate()` no reciben un `Problem` como
  parámetro (a diferencia de la firma de la interfaz). Para no romper
  el contrato ni forzar una firma inconsistente, se dejaron los métodos
  tal como los muestra el diagrama, sin el `implements Handler`.
- **`DecanaturaHandler` siempre acepta el problema:** al ser la última
  instancia de la cadena, se sobrescribe `canResolve()` para que
  garantice que ningún problema quede sin resolver.

## Cómo compilar y ejecutar

Requiere JDK 8 o superior.

```bash
# Compilar
javac *.java

# Ejecutar
java Main
```

## Funcionalidad del Main (consola)

Al iniciar, `Main` arma la cadena completa (Monitor → Profesor →
Coordinador → Secretaria → Decanatura), cada uno con su `SubHandler`
asignado, y carga 5 problemas de ejemplo (uno por nivel). Luego
presenta un menú interactivo:

1. **Agregar un problema nuevo** – pide descripción y nivel (1 a 5).
2. **Ver problemas registrados** – lista todos con su estado (resuelto o no).
3. **Procesar el siguiente problema no resuelto** – ejecuta la cadena
   sobre el primer problema pendiente.
4. **Procesar TODOS los problemas** – recorre la lista completa.
5. **Ver estado de los SubHandlers** – muestra quién está ocupado y con qué problema.
6. **Recargar problemas de ejemplo**.
0. **Salir**.

### Ejemplo de flujo

```
>> Procesando: "Reclamo por nota de un parcial" (nivel 2)
   -> [Profesor] delega el problema "Reclamo por nota de un parcial" a Prof. Ramírez
>> Estado final: RESUELTO por ProfesorHandler
```

Un problema de nivel 5 (o cualquier nivel que ningún handler intermedio
pueda resolver) siempre termina siendo atendido por `DecanaturaHandler`,
que actúa como última instancia de la cadena.
