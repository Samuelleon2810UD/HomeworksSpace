# Taller para la Casa – User Interface Patterns (Java)

Sistema de Configuración y Reservas Universitarias: controlador de interfaz en consola que aplica un patrón por cada categoría de UI Patterns, sin Dark Patterns.

## Estructura

```
src/
├── navigation/BreadcrumbNavigator.java   Breadcrumbs + Tabs
├── content/TarjetaElemento.java          Progressive Disclosure (acordeón)
├── input/ValidadorForgiving.java         Forgiving Format (regex)
├── state/GestorSesionUI.java             Lazy Registration + Completeness Meter + Toast
└── main/Main.java                        5 casos de prueba (+ caso extra de salidas éticas)
```

## Compilar y ejecutar

```bash
mkdir out
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -Dfile.encoding=UTF-8 -cp out main.Main
```

La salida de la ejecución está en `salida_consola.txt`.

## Casos de prueba

| Caso | Patrón | Resultado |
|------|--------|-----------|
| 1 | Navegación (Breadcrumb + Tabs) | La ruta cambia: `Inicio > Catálogo > Servidor Principal`, `Inicio > Reservas > Laboratorio de Redes` |
| 2 | Progressive Disclosure | `verAvanzado=false` muestra nombre y estado; con `true` muestra IP, puerto y RAM |
| 3 | Forgiving Format | `"+57 (310) 999-8877"` → `"3109998877"` sin excepciones |
| 4 | Completeness Meter | 0% → 33% → 67% → 100% |
| 5 | Lazy Registration + Toast | Sin sesión: borrador y pedido de registro; con sesión: Toast de éxito |

## Reflexión: cómo se evitan los Dark Patterns

La implementación evita los Dark Patterns porque nunca bloquea ni culpa al usuario: el `ValidadorForgiving` acepta teléfonos y fechas en cualquier formato y los normaliza sin lanzar errores, y el Lazy Registration permite armar toda la reserva sin cuenta, conservando un borrador temporal en lugar de obligar a registrarse primero ni perder lo escrito. No hay *Roach Motel* porque `cancelarReserva()` y `cerrarSesion()` se ejecutan en un solo paso, igual de fáciles que crear la reserva; no hay *Confirmshaming* porque los mensajes son neutros ("Reserva cancelada.", "Sesión cerrada.") sin culpabilizar la decisión; no hay *Drip Pricing* ni *Misdirection* porque el Completeness Meter muestra desde el inicio qué campos faltan y el Progressive Disclosure oculta solo detalles técnicos opcionales, nunca información necesaria para decidir, y los despliega con una acción explícita.
