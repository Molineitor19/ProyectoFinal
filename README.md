#  README - Proyecto "No Choques"

##  Descripción del Proyecto

**No Choques** es un videojuego desarrollado en Java Swing, donde el
jugador controla un vehículo (carro, moto o bicicleta --- cada uno con
variantes estándar y PRO) y debe esquivar obstáculos que aparecen desde
la derecha de la pantalla.

El juego incluye:

-   Un menú principal estilizado\
-   Selector de vehículos\
-   Animaciones del jugador y enemigos\
-   Sistema de puntuación y vidas\
-   Cambio de control (mouse/teclado)\
-   Pantalla Game Over personalizada\
-   Arquitectura con varios patrones de diseño GoF

El objetivo es resistir el mayor tiempo posible esquivando obstáculos y
acumulando puntos.

##  Cómo Funciona el Juego

### 1. Menú Principal

Permite: - Iniciar el juego\
- Seleccionar vehículo\
- Salir

Los botones tienen estilo personalizado

------------------------------------------------------------------------

### 2. Selector de Vehículos

El jugador puede elegir entre 6 vehículos:

-   Carro\
-   Carro Pro\
-   Moto\
-   Moto Pro\
-   Bicicleta\
-   Bicicleta Pro

Cada vehículo modifica: - **Velocidad de movimiento (paso)**\
- **Velocidad de animación (delay)**\
- **Sprites**

------------------------------------------------------------------------

### 3. Vista del Juego

La clase `Vista` contiene la lógica principal:

-   Animación del jugador\
-   Movimiento por teclado o mouse\
-   Aparición y movimiento de enemigos\
-   Detección de colisiones\
-   Actualización de puntuación y vidas\
-   Gestión del Game Over

Los enemigos reaparecen cuando salen de pantalla y otorgan puntos si son
esquivados.

------------------------------------------------------------------------

### 4. Game Over

Incluye una ventana personalizada con 3 opciones:

-   Reiniciar\
-   Volver al menú\
-   Salir

------------------------------------------------------------------------

## 🧩 Patrones GoF Implementados

### ✔️ 1. Singleton --- `GameManager`

Administra:

-   Puntuación\
-   Vidas\
-   Estado del juego

Solo existe una instancia global.

**Razón:** El estado del juego debe ser único y accesible desde
cualquier parte.

------------------------------------------------------------------------

### ✔️ 2. Observer --- `SujetoObservable`, `Observador`, `Vista`

El `GameManager` notifica a las vistas cuando:

-   Cambia la puntuación\
-   Cambian las vidas\
-   Ocurre el Game Over

**Razón:** Desacoplar la lógica del juego de la interfaz gráfica.

------------------------------------------------------------------------

### ✔️ 3. Strategy --- Movimiento de enemigos

Clases:

-   `EnemyMovementStrategy`\
-   `StraightMovement`\
-   `ZigZagMovement`

**Razón:** Permitir diferentes comportamientos de movimiento sin
modificar la clase Enemy.

------------------------------------------------------------------------

### ✔️ 4. Decorator --- Sistema de vehículos

Clases:

-   `Desplazamiento`\
-   `DesplazamientoDecorator`\
-   `Carro`, `Moto`, `Bicicleta`

**Razón:** Extender dinámicamente el comportamiento del movimiento sin
alterar el personaje base.

------------------------------------------------------------------------

### ✔️ 5. Abstract Factory / Factory Method --- Fabricación de vehículos

Clases:

-   `VehiculoFactory`\
-   `CarroFactory`\
-   `MotoFactory`\
-   `BicicletaFactory`

**Razón:** Centralizar cómo se crean los vehículos y sus parámetros.

------------------------------------------------------------------------

### ✔️ 6. Facade --- `ImageFacade`

Abstrae la carga de imágenes.

**Razón:** Evita duplicar código y simplifica manejo de errores.

------------------------------------------------------------------------

## ❌ Patrones GoF NO implementados y por qué

El proyecto no requiere los 23 patrones GoF.\
Los restantes no se implementaron por estas razones:

### 🟥 Patrones Creacionales no usados

-   **Prototype:** No se necesita clonación masiva.\
-   **Builder:** No hay objetos con construcción compleja.

### 🟥 Patrones Estructurales no usados

-   **Adapter:** No se integran librerías incompatibles.\
-   **Bridge:** No se requiere desacoplar plataformas.\
-   **Composite:** No existen estructuras jerárquicas.\
-   **Flyweight:** No hay alto volumen de objetos repetidos.\
-   **Proxy:** No se manipulan recursos remotos/pesados.

### 🟥 Patrones Comportamentales no usados

-   **Chain of Responsibility:** No hay cadenas de responsabilidad.\
-   **State:** Los estados del juego son simples.\
-   **Mediator:** La comunicación actual ya es simple con GameManager.\
-   **Memento:** No hay guardado/restauración del estado.\
-   **Interpreter / Visitor:** No se procesan lenguajes ni jerarquías
    complejas.\
-   **Command:** Las acciones son simples y directas.

------------------------------------------------------------------------

## 👥 Integrantes del Proyecto

-   **Kaleth Molina Diaz - 20232020096**\
-   **Nelson David Molina Ramos - 20222020121**

