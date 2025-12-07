# Simulación Decorator - Movimiento de Vehículos

## Descripción del Proyecto

Este proyecto implementa una simulación gráfica que demuestra el funcionamiento del **Patrón de Diseño Decorator** en **Java SE**.  
La aplicación muestra diferentes vehículos desplazándose a través de una interfaz construida con **JFrame (Swing)**, donde cada tipo de vehículo tiene una velocidad distinta.  
El proyecto fue desarrollado en **NetBeans IDE** y utiliza **Apache Tomcat** para pruebas locales o despliegue de componentes.

---

## Tecnologías Utilizadas

| Componente | Descripción |
|-------------|-------------|
| **Lenguaje** | Java SE |
| **Entorno de desarrollo** | NetBeans IDE |
| **Servidor** | Apache Tomcat (para pruebas locales o despliegue de componentes) |
| **Interfaz gráfica** | JFrame (Swing) |
| **Patrón de diseño** | Decorator |

---

## Objetivo

El objetivo principal es ilustrar el uso del **Patrón Decorator** mediante una animación visual en la que diversos vehículos (bicicletas, motos y carros) se mueven por la pantalla con diferentes velocidades.  
Cada vehículo reaparece desde el lado izquierdo una vez cruza la pantalla, y aleatoriamente puede aparecer otro vehículo o el mismo con una velocidad distinta.

---

## Clasificación de Velocidades

Cada vehículo posee una velocidad relativa, determinada según su tipo y si tiene mejoras (pro o tuneado):

| Vehículo         | Velocidad Relativa     |
|------------------|------------------------|
| Bicicleta normal | Muy lenta              |
| Bicicleta pro    | Lenta                  |
| Moto normal      | Media                  |
| Carro normal     | Rápida                 |
| Carro tuneado    | Muy rápida             |
| Moto pro         | La más veloz           |

---

## Estructura del Proyecto

## Estructura del proyecto

```
src/
PatronDecoratorDesplazamiento
├── Imagenes.imagenes # Carpeta de recursos gráficos (sprites y fondos)
│
├── Vista # Interfaz gráfica
│ └── Vista.java
│
├── controlador # Clase principal de ejecución
│ └── AplMain.java
│
└── modelo # Clases del modelo y patrón Decorator
├── Bicicleta.java
├── Carro.java
├── Moto.java
├── Personaje.java
├── Desplazamiento.java
└── DesplazamientoDecorator.java

```

---



---

## Funcionamiento del Patrón Decorator

El **Patrón Decorator** permite agregar comportamientos adicionales a un objeto sin modificar su estructura original.  
En este caso, se aplica para cambiar dinámicamente la velocidad de desplazamiento de los vehículos.

- `Desplazamiento`: interfaz base que define el método de movimiento.  
- `Personaje`: clase concreta que representa un vehículo básico.  
- `DesplazamientoDecorator`: clase abstracta que extiende el comportamiento del objeto base.  
- Subclases decoradoras: modifican la velocidad para simular versiones mejoradas (pro o tuneadas).

Este diseño facilita la extensión de características sin alterar las clases principales, fomentando la **flexibilidad**, **reutilización del código** y **principios SOLID**.

---

## Ejecución del Proyecto

1. Clonar el repositorio:
   ```bash
   https://github.com/Molineitor19/PatronDecorate.git
Abrir el proyecto en NetBeans IDE.

Verificar que esté configurado Apache Tomcat (si se desea usar para despliegue).

Ejecutar la clase principal:

Copiar código
controlador/AplMain.java
Observar la simulación donde los vehículos se desplazan y reaparecen con distintas velocidades de manera aleatoria.

Requisitos
Java JDK 8 o superior

NetBeans 12 o superior

Apache Tomcat 9 o superior

Sistema operativo compatible con Swing (Windows / Linux / MacOS)

Vista de Ejemplo

<img width="959" height="501" alt="image" src="https://github.com/user-attachments/assets/bbb33334-4adb-4752-9aab-80b196862ce5" />



Autores

Kaleth Molina — Código: 20232020096

Nelson Molina — Código: 20222020121
