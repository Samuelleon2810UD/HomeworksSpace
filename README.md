```markdown
# 🦆 DuckHuntU - Simulación de Patrones de Diseño (Strategy & Bridge)

**DuckHuntU** es un proyecto educativo desarrollado en Java que ilustra la implementación de los patrones de diseño **Strategy** y **Bridge** para la gestión desacoplada de comportamientos y características en entidades del sistema (patos).

---

## 📌 Tabla de Contenidos

- [Descripción General](#-descripción-general)
- [Patrones de Diseño Aplicados](#-patrones-de-diseño-aplicados)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Requisitos del Sistema](#-requisitos-del-sistema)
- [Instalación y Configuración](#-instalación-y-configuración)
- [Ejecución e Interacción](#-ejecución-e-interacción)
- [Extensibilidad y Escalabilidad](#-extensibilidad-y-escalabilidad)

---

## 📖 Descripción General

La aplicación simula un entorno interactivo en consola donde coexisten diversos tipos de patos (*Mallard Duck*, *Red Duck*, *Decoy Duck*, *Ule Duck*). Cada tipo de pato posee variaciones únicas en su aspecto visual, los sonidos que emite y la forma en que vuela.

A través del desacoplamiento de clases y el uso extensivo de **composición**, el sistema permite **modificar o equipar comportamientos en tiempo de ejecución** (por ejemplo, equipar o desequipar un *Super Mega Jetpack* a un pato en particular).

---

## 🎨 Patrones de Diseño Aplicados

### 1. Patrón Strategy (Comportamientos Dinámicos)
Se utiliza para encapsular las familias de algoritmos de **Vuelo** (`FlyBehavior`) y **Sonido** (`SoundBehavior`).
- **Interfaces:** `FlyBehavior`, `SoundBehavior`.
- **Implementaciones de Vuelo:** `FlyWithWings`, `FlyWithSuperMegaJetPack`, `NoFly`.
- **Implementaciones de Sonido:** `SoundQuack`, `ArtificialSoundQuack`, `NoSound`.
- **Ventaja:** Permite cambiar el comportamiento de vuelo o sonido de cualquier pato dinámicamente mediante métodos *setter* (`setFlyBehavior`, `setSoundBehavior`).

### 2. Patrón Bridge (Separación de Abstracción e Implementación)
Separación de la jerarquía conceptual del objeto (`Duck`) de la jerarquía de ejecución de sus comportamientos concretos (`Behavior`), evitando el problema de la **explosión cartesiana de clases** producida por la herencia tradicional.

---

## 📂 Estructura del Proyecto

```text
DuckHuntU/
├── src/
│   └── main/
│       └── java/
│           ├── Behavior/
│           │   ├── Fly/
│           │   │   ├── FlyBehavior.java
│           │   │   ├── FlyWithWings.java
│           │   │   ├── FlyWithSuperMegaJetPack.java
│           │   │   └── NoFly.java
│           │   └── Sound/
│           │       ├── SoundBehavior.java
│           │       ├── SoundQuack.java
│           │       ├── ArtificialSoundQuack.java
│           │       └── NoSound.java
│           ├── Ducks/
│           │   ├── Duck.java             # Clase abstracta base
│           │   ├── MallardDuck.java
│           │   ├── RedDuck.java
│           │   ├── DecoyDuck.java
│           │   └── UleDuck.java
│           └── com/mycompany/duckhuntu/
│               └── DuckHuntU.java        # Punto de entrada (main)
├── pom.xml
└── README.md

```

---

## ⚙️ Requisitos del Sistema

* **JDK:** Java 17 o superior (Recomendado Java 21 / 24).
* **Herramienta de Construcción:** Apache Maven 3.6+.
* **IDE Soportados:** Visual Studio Code, IntelliJ IDEA, Eclipse o NetBeans.

---

## 🚀 Instalación y Configuración

1. **Clonar el repositorio:**
```bash
git clone [https://github.com/tu-usuario/DuckHuntU.git](https://github.com/tu-usuario/DuckHuntU.git)
cd DuckHuntU

```


2. **Verificar la versión de Java en el `pom.xml`:**
Asegúrate de que la versión en `<maven.compiler.release>` coincida con tu JDK instalado:
```xml
<properties>
    <maven.compiler.source>21</maven.compiler.source>
    <maven.compiler.target>21</maven.compiler.target>
    <maven.compiler.release>21</maven.compiler.release>
</properties>

```


3. **Compilar el proyecto con Maven:**
```bash
mvn clean compile

```



---

## 🎮 Ejecución e Interacción

Para iniciar la aplicación por consola, ejecuta el método `main` en `DuckHuntU.java` o utiliza Maven:

```bash
mvn exec:java -Dexec.mainClass="com.mycompany.duckhuntu.DuckHuntU"

```

### Menú Interactivo en Consola:

```text
=================================
   🦆 MENÚ INTERACTIVO DUCKHUNT 🦆
=================================
1. Ver patos actuales y realizar sus acciones
2. Añadir un nuevo pato
3. Equipar / Desequipar Jetpack (Modificar vuelo)
4. Salir

```

---

## 📈 Extensibilidad y Escalabilidad

Para agregar una nueva funcionalidad o comportamiento (ej. `SwimBehavior`):

1. **Crear el paquete:** `Behavior/Swim/`
2. **Definir la interfaz:** `SwimBehavior.java`
3. **Implementar las variaciones:** `SwimWithFins.java`, `NoSwim.java`, etc.
4. **Agregar la referencia en `Duck.java`:**
```java
protected SwimBehavior swimBehavior;

public void performSwim() {
    swimBehavior.swim();
}

```



Esto garantiza cumplir con el principio **Open/Closed** (*Abierto a extensión, cerrado a modificación*).



```
