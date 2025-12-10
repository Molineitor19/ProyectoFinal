# No Choques - Videojuego de Esquivar Obstáculos

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/Java%20Swing-FF9800?style=flat&logo=java&logoColor=white)
![Design Patterns](https://img.shields.io/badge/Design%20Patterns-GoF-blueviolet?style=flat)
![License](https://img.shields.io/badge/License-MIT-green)

**Un juego de destreza y reflejos desarrollado en Java con arquitectura profesional.**

---

## Tabla de Contenidos
- Vista General
- Características Principales
- Arquitectura y Patrones
- Mecánicas de Juego
- Cómo Ejecutar
- Estructura del Proyecto
- Integrantes
- Contacto y Contribuciones
- Métricas del Proyecto
- Futuras Mejoras

---

## Vista General

**No Choques** es un videojuego arcade desarrollado en Java Swing donde el jugador controla un vehículo que debe esquivar obstáculos en una carretera infinita.

**Objetivo:** sobrevivir el mayor tiempo posible acumulando puntos.

---

## Características Principales

### Interfaz Visual Mejorada
- Menú principal con efectos visuales y gradientes  
- Selector de vehículos con tarjetas interactivas  
- Sistema de puntuación en tiempo real  
- Diálogos personalizados y animaciones  
- Modo pantalla completa opcional  

### Sistema de Control Dual
- **Mouse:** movimiento vertical  
- **Teclado:** flechas para movimiento completo  
- **M:** alternar entre teclado y mouse  

### Sistema de Vehículos

| Vehículo | Variante | Velocidad | Característica |
|---------|----------|-----------|----------------|
| Carro   | Normal   | Media     | Tamaño grande, estable |
| Carro   | Pro      | Alta      | Mejor aceleración |
| Moto    | Normal   | Alta      | Ágil, tamaño pequeño |
| Moto    | Pro      | Máxima    | Velocidad extrema |
| Bici    | Normal   | Baja      | Maniobrabilidad excelente |
| Bici    | Pro      | Media     | Balance perfecto |

### Sistema de Progresión
- Puntos por obstáculos esquivados  
- Sistema de vidas (actualmente 1 vida)  
- Puntuación persistente por sesión  
- Pantalla de Game Over con opciones  

---

## Arquitectura y Patrones

### Patrones Implementados

#### **Singleton – GameManager**
Control centralizado del estado del juego.

```java
GameManager.getInstance();
Administra puntuación, vidas y estado general.

Observer – Sistema de Notificaciones
Comunicación desacoplada entre componentes.

java
Copiar código
gameManager.agregarObservador(vista);
gameManager.notificar("puntos", 100);
Actualiza la interfaz en tiempo real.

Strategy – Comportamiento de Enemigos
Permite intercambiar algoritmos de movimiento.

java
Copiar código
interface EnemyMovementStrategy {
    void mover();
}
Estrategias incluidas:

Movimiento recto

ZigZag

Decorator – Mejoras de Vehículos
Extiende comportamiento sin modificar la clase base.

java
Copiar código
Desplazamiento vehiculo = new Carro();
vehiculo = new TurboDecorator(vehiculo);
Factory Method – Creación de Vehículos
Centraliza la construcción de objetos vehículo.

java
Copiar código
VehiculoFactory factory = new CarroFactory();
Vehiculo carro = factory.crearVehiculo();
Facade – Manejo de Recursos
Simplifica carga de imágenes y manejo de archivos.

java
Copiar código
Image imagen = ImageFacade.cargar("/ruta/imagen.png");
Patrones No Implementados
Patrón	Razón
Builder	No hay construcción compleja
Composite	No existen jerarquías profundas
State	Estados manejados directamente
Command	No se requieren acciones encapsuladas
Memento	No hay sistema de guardado
Visitor	No se recorren estructuras complejas

Mecánicas de Juego
Controles
Tecla / Acción	Función
Flechas	Movimiento (modo teclado)
Mouse	Movimiento vertical (modo mouse)
M	Cambiar modo de control
ESC	Salir de pantalla completa

Sistema de Puntuación
sql
Copiar código
+---------------+---------------+-------------------+
|   Acción      |   Puntos      |   Descripción     |
+---------------+---------------+-------------------+
|  Esquivar     |   +10         | Por obstáculo     |
|  Sobrevivir   |  Tiempo x 1   | Cada segundo vivo |
|  Colisión     |   Game Over   | Fin del juego     |
+---------------+---------------+-------------------+
Flujo del Juego
markdown
Copiar código
Menú Principal → Selector de Vehículo → Juego en Progreso
        ↑               ↓                    ↓
        └─────── Game Over ←───── ¿Colisión? ←─┘
                        ↓
             Reiniciar / Menú / Salir
Cómo Ejecutar
Prerrequisitos
Java JDK 8 o superior

Cualquier IDE (IntelliJ, Eclipse, NetBeans) o línea de comandos

Pasos de Ejecución
Clonar o descargar el proyecto

Abrir en tu IDE

Compilar

Ejecutar MenuPrincipal.java

Compilación por Consola
bash
Copiar código
# Compilar
javac -d bin src/**/*.java

# Ejecutar
java -cp bin Vista.MenuPrincipal
Estructura del Proyecto
bash
Copiar código
src/
├── Vista/
│   ├── MenuPrincipal.java
│   ├── Vista.java
│   ├── SelectorVehiculo.java
│   └── GameOverDialog.java
├── adaptadores/
│   ├── ControlTeclado.java
│   ├── ControlMouse.java
│   └── MovimientoJugador.java
├── controlador/
│   ├── GameManager.java
│   └── ImageFacade.java
├── enemigos/
│   ├── Enemy.java
│   ├── EnemyMovementStrategy.java
│   └── StraightMovement.java
├── fabrica/
│   ├── VehiculoFactory.java
│   └── Factories específicas
├── modelo/
│   ├── Desplazamiento.java
│   ├── DesplazamientoDecorator.java
│   └── Vehículos
├── observador/
│   ├── Observador.java
│   ├── SujetoObservable.java
│   └── ObservadorPuntuacion.java
└── Imagenes/imagenes/
Integrantes
Desarrollador	Código	Rol
Kaleth Molina Díaz	20232020096	Arquitectura y Patrones
Nelson David Molina Ramos	20222020121	Interfaz y Gameplay

Contacto y Contribuciones
Proyecto académico con fines demostrativos
Basado en mejores prácticas de diseño de software

Universidad Distrital Francisco José de Caldas
Ingeniería de Sistemas – Patrones de Diseño 2024

Métricas del Proyecto
6 patrones GoF implementados

Más de 15 clases organizadas por responsabilidad

Interfaz completa con efectos visuales

Rendimiento optimizado para Swing

Futuras Mejoras
Sistema de power-ups (Observer)

Dificultad progresiva (Strategy)

Récords guardados (Memento)

Efectos de sonido (Observer)

Animaciones transicionales mejoradas
