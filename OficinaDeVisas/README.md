# Taller para la Casa – Antipatrones: La Oficina de Visas del Caos (Java)

Universidad Distrital Francisco José de Caldas · Ingeniería de Sistemas
Asignatura: Modelos de Programación · Docente: Bocanegra

> Integrantes: completar con los nombres y códigos del equipo.

## Cómo compilar y ejecutar

```bash
mkdir out
javac -encoding UTF-8 -d out $(find src -name '*.java')
java -cp out app.Main
```

Requiere JDK 17 o superior (usa `record`). La salida completa de los 5 casos está en `salida_consola.txt`.

## Organización por paquetes

| Paquete | Contenido | Rol |
|---|---|---|
| `legado` | `OficinaDeTodo`, `CalculadoraDeTarifas`, `EvaluadorMagico`, `SolicitudAcoplada` (+ servicios heredados) | Código heredado, conservado **solo como evidencia** |
| `consulado` | `ValidadorDePasaporte`, `CajaDePagos`, `ArchivoDeExpedientes`, `NotificadorDeCorreo`, `TramiteDeVisa` | Rescate del God Object (una clase por responsabilidad) |
| `tarifas` | `TarifaBase`, `TarifaStrategy`, `TarifaTurista/Estudiante/Trabajo`, `CalculadoraDeTarifas`, `CatalogoDeTarifas` | Rescate del Copy-Paste (Strategy) |
| `visas` | `TipoVisa` (enum), `EvaluadorDeVisas`, `ResultadoEvaluacion`, `EstadoEvaluacion`, `TipoVisaInvalidoException` | Rescate de los Magic Numbers |
| `solicitudes` | `Solicitud`, `SolicitudObserver`, `EstadoSolicitud` | Sujeto del Observer |
| `notificaciones` | `CorreoObserver`, `CitasObserver`, `PagosObserver` | Observadores concretos |
| `reporte`, `util` | `TablaDeAntipatrones`, `Consola` | Presentación en consola |
| `app` | `Main` | Los 5 casos de prueba |

## Parte 1 – Cacería de antipatrones

| Fragmento | Antipatrón | Síntoma que lo delata | Consecuencia en el consulado |
|---|---|---|---|
| 1. `OficinaDeTodo` (la ventanilla de Ramiro) | **God Object** | Una sola clase valida el pasaporte, cobra, guarda el expediente y envía el correo; cuatro motivos para cambiar en un mismo archivo. | Punto único de fallo: si Ramiro se enferma (o esa clase falla) el consulado entero se detiene; es imposible probar, reutilizar o cambiar una tarea sin arriesgar las otras tres. |
| 2. `CalculadoraDeTarifas` (la tarifa clonada) | **Copy-Paste Programming** | `50 * meses; total + 10;` repetido en tres métodos casi idénticos. | Cada cambio hay que hacerlo N veces; al subir la tarifa se corrigieron dos y la tercera quedó con el precio viejo (cobros inconsistentes). |
| 3. `EvaluadorMagico` (los números misteriosos) | **Magic Numbers** | `tipo == 3`, `edad > 17`, `edad < 66`, `0.5`, sin nombre ni documentación; un tipo desconocido cae a `RECHAZADA` sin explicación. | Nadie confirma qué significa cada número, el código es frágil de modificar y los errores (ej. tipo 9) se tragan en silencio. |
| 4. `SolicitudAcoplada` (la cancelación en cadena) | **Acoplamiento fuerte** | La solicitud hace `new` de correo, citas y pagos y los llama a mano, uno por uno. | Es fácil olvidar un aviso: un viernes se olvidó la caja y a un viajero se le perdió el reembolso; agregar un aviso obliga a editar la solicitud. |

## Parte 2 – El rescate

- **God Object → responsabilidad única.** Cuatro clases pequeñas (validar, cobrar, archivar, notificar) y `TramiteDeVisa`, que solo coordina el orden y recibe sus colaboradores por constructor.
- **Copy-Paste → Strategy.** `TarifaBase` calcula **una sola vez** `precio por mes × meses + cargo fijo` y es el único lugar donde se cambia el precio base. Cada tipo de visa solo aporta su ajuste (`TarifaTurista`, `TarifaEstudiante` ×0.5, `TarifaTrabajo` ×1.5) y `CalculadoraDeTarifas` elige la estrategia en un `EnumMap`, sin `if` por tipo.
- **Magic Numbers → enum y constantes.** `TipoVisa` (`TURISTA`, `ESTUDIANTE`, `TRABAJO`) lleva su código y su rango de edad. `desdeCodigo(9)` lanza `TipoVisaInvalidoException` con el mensaje "Tipo de visa invalido: 9. Tipos validos: …". Todos los demás valores (`LONGITUD_MINIMA`, `CARGO_FIJO`, factores de tarifa, datos de prueba en `Main`) son constantes con nombre.
- **Acoplamiento fuerte → Observer.** `Solicitud` solo conoce la interfaz `SolicitudObserver`; correo, citas y pagos se suscriben desde fuera. Un aviso nuevo es una clase nueva y una línea de suscripción, sin tocar `Solicitud`.

## Parte 3 – Salida en consola de los 5 casos

Ver `salida_consola.txt` (generada con `java -cp out app.Main`). Resumen de resultados:

| Caso | Resultado observado |
|---|---|
| 1 God Object | Ambas versiones aprueban; la salida muestra a `OficinaDeTodo` haciendo todo frente a `ValidadorDePasaporte`, `CajaDePagos`, `ArchivoDeExpedientes` y `NotificadorDeCorreo`. |
| 2 Copy-Paste | Subir 50 → 60: la heredada necesita **3** ediciones (contador = 3) y Strategy **1**. Precios idénticos: 190 / 95 / 285 (3 meses). Además se muestra el error real: con 2 ediciones el trabajo queda en 240 en vez de 285. |
| 3 Magic Numbers | Tipo 2 / edad 20 y `TipoVisa.ESTUDIANTE`: ambos `APROBADA`. Tipo 9: la heredada devuelve `RECHAZADA` sin explicar; la nueva explica el motivo. |
| 4 Acoplamiento | La heredada avisa a correo y citas pero deja a la vista el **reembolso olvidado**; la nueva avisa a correo, citas y pagos. |
| 5 Integración | Solicitar → evaluar (APROBADA) → cobrar ($555.0 por 6 meses de visa de trabajo) → cancelar (3 avisos) y tabla final de los 4 antipatrones. |

## Reflexión: cómo evitamos volver a caer en los antipatrones

Cada clase de la versión nueva tiene una sola razón para cambiar, por lo que no queda ningún "nuevo Ramiro": `TramiteDeVisa` solo coordina y delega. La tarifa base vive en un único campo (`TarifaBase`) y la fórmula existe una sola vez, de modo que subir el precio o agregar un tipo de visa nunca exige copiar lógica. Los números y textos con significado se volvieron `enum` y constantes con nombre, y los tipos inválidos se rechazan con un mensaje claro en lugar de en silencio. Por último, la solicitud ya no conoce a los servicios: publica el evento de cancelación y los observadores reaccionan, así que añadir un aviso nuevo no obliga a tocar la solicitud ni se puede "olvidar" un servicio ya suscrito. Como práctica adicional, las dependencias se inyectan por constructor, lo que mantiene el acoplamiento bajo y facilita las pruebas.
