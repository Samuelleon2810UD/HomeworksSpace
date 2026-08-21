# WeatherIndicator

Aplicación de consola en Java que simula una estación meteorológica implementando el **patrón de diseño Observer**. Permite registrar y remover dinámicamente distintas vistas (displays) que reaccionan a nuevas mediciones de temperatura, humedad y presión.

## Patrón utilizado

- **Subject** (`Subject.Subject`, implementado por `Subject.WeatherData`): mantiene el estado de las mediciones y la lista de observadores; notifica a todos los observadores registrados cuando cambian los datos.
- **Observer** (`Observer.Observer`): interfaz que implementan todas las vistas para recibir las actualizaciones (`UpdateDisplay`).
- **Display** (`Display.Display`): interfaz que define cómo cada vista muestra su información en consola (`display()`).

Cada vista (`CurrentConditionDisplay`, `ForeCastDisplay`, `HeatIndexDisplay`, `StatisticsDisplay`, `ThirdPartyDisplay`) implementa ambas interfaces: se suscribe como `Observer` para recibir datos y expone `display()` para mostrarlos.

## Estructura del proyecto

```
com.mycompany.weatherindicator
└── WeatherIndicator.java     # Clase principal con el menú de consola

Subject
├── Subject.java              # Interfaz del sujeto observado
└── WeatherData.java          # Implementación del sujeto (estación meteorológica)

Observer
└── Observer.java             # Interfaz de los observadores

Display
├── Display.java              # Interfaz de presentación
├── CurrentConditionDisplay.java
├── ForeCastDisplay.java
├── HeatIndexDisplay.java
├── StatisticsDisplay.java
└── ThirdPartyDisplay.java
```

## Vistas disponibles

| Vista                | Descripción                                                        |
|-----------------------|---------------------------------------------------------------------|
| Condiciones Actuales   | Muestra la última temperatura, humedad y presión registradas.       |
| Pronostico             | Predice el clima comparando la presión actual con la anterior.      |
| Indice de Calor        | Calcula y muestra el índice de calor (heat index).                  |
| Estadisticas           | Guarda y muestra el historial completo de todas las mediciones.     |
| Terceros               | Muestra únicamente la presión, simulando un servicio externo.       |

## Menú de la aplicación

Al ejecutar `WeatherIndicator.main`, se despliega un menú interactivo por consola:

```
===== MENU - MONITOREO DE ESTACION METEOROLOGICA =====
1. Ver estado de todas las vistas (activas/inactivas)
2. Agregar (activar) una vista
3. Quitar (desactivar) una vista
4. Ingresar nuevas mediciones (temperatura, humedad, presion)
5. Mostrar el contenido de las vistas activas
0. Salir
```

- **Opción 1**: lista el catálogo de vistas disponibles indicando si están activas o inactivas.
- **Opción 2**: registra una vista (por nombre exacto) como observador de `WeatherData`.
- **Opción 3**: remueve una vista activa de la lista de observadores.
- **Opción 4**: solicita nuevos valores de temperatura, humedad y presión, y notifica automáticamente solo a las vistas activas.
- **Opción 5**: imprime en consola el resultado de `display()` de cada vista actualmente activa.
- **Opción 0**: finaliza la ejecución.

Las vistas se crean todas al iniciar el programa, pero **no quedan suscritas por defecto**: el usuario decide cuáles activar desde el menú, lo que permite añadir o quitar vistas en tiempo de ejecución sin reiniciar la aplicación.

## Requisitos

- Java 8 o superior (usa características de switch clásico, compatible con versiones anteriores).
- Maven (proyecto gestionado con `pom.xml`).

## Ejecución

```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.mycompany.weatherindicator.WeatherIndicator"
```

O bien, desde un IDE como NetBeans/IntelliJ, ejecutar directamente la clase `WeatherIndicator`.

## Notas de corrección

Durante la integración del menú se corrigieron dos errores del código original:

1. **`WeatherData`**: la lista de observadores no se inicializaba, lo que provocaba `NullPointerException` al registrar la primera vista. Se agregó un constructor que la inicializa.
2. **`HeatIndexDisplay`**: el índice de calor no se recalculaba al recibir nuevas mediciones (`UpdateDisplay` no invocaba `getHeatIndex()`), por lo que siempre se mostraba en `0.0`. Se corrigió para que se calcule con cada actualización.
