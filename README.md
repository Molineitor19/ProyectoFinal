Tabla de Contenidos

Vista General

Características Principales

Arquitectura y Patrones

Mecánicas de Juego

Cómo Ejecutar

Estructura del Proyecto

Integrantes

Contacto y Contribuciones

Métricas del Proyecto

Futuras Mejoras

Vista General

No Choques es un videojuego arcade desarrollado en Java Swing donde el jugador controla un vehículo que debe esquivar obstáculos en una carretera infinita. Con gráficos atractivos, controles intuitivos y un sistema de progresión, ofrece una experiencia de juego envolvente y desafiante.

Objetivo: Sobrevivir el mayor tiempo posible, acumulando puntos y demostrando tus reflejos.

Características Principales
Interfaz Visual Mejorada

Menú principal con efectos visuales y gradientes

Selector de vehículos con tarjetas interactivas

Sistema de puntuación en tiempo real

Diálogos personalizados y animaciones

Modo pantalla completa opcional

Sistema de Control Dual

Mouse: movimiento vertical intuitivo

Teclado: flechas para movimiento completo

Tecla M: cambiar dinámicamente entre modos

Sistema de Vehículos
Vehículo	Variante	Velocidad	Característica
Carro	Normal	Media	Tamaño grande, estabilidad
Carro	Pro	Alta	Mejor aceleración y control
Moto	Normal	Alta	Tamaño pequeño, ágil
Moto	Pro	Máxima	Velocidad extrema
Bici	Normal	Baja	Maniobrabilidad excelente
Bici	Pro	Media	Balance perfecto
Sistema de Progresión

Puntos por obstáculos esquivados

Sistema de vidas (actualmente 1 vida)

Puntuación persistente por sesión

Pantalla de Game Over con opciones claras

Arquitectura y Patrones

El proyecto implementa una arquitectura limpia y modular basada en GoF Design Patterns:

Patrones Implementados
Singleton – GameManager

Control centralizado del estado del juego:

GameManager.getInstance();

Observer – Sistema de Notificaciones
gameManager.agregarObservador(vista);
gameManager.notificar("puntos", 100);

Strategy – Movimiento de Enemigos
interface EnemyMovementStrategy { void mover(); }
class StraightMovement implements EnemyMovementStrategy { ... }

Decorator – Mejoras de Vehículos
Desplazamiento vehiculo = new Carro();
vehiculo = new TurboDecorator(vehiculo);

Factory Method – Creación de Vehículos
VehiculoFactory factory = new CarroFactory();
Vehiculo carro = factory.crearVehiculo();

Facade – Manejo de Recursos
Image img = ImageFacade.cargar("/ruta/imagen.png");

Patrones No Implementados (y por qué)
Patrón	Razón
Builder	Construcción no compleja
Composite	No hay jerarquías complejas
State	Los estados se manejan directamente
Command	Acciones simples
Memento	No hay sistema de guardado
Visitor	No existen estructuras que lo requieran
Mecánicas de Juego
Controles
Tecla/Acción	Función
Flechas	Movimiento
Mouse	Movimiento vertical
M	Cambiar modo
ESC	Salir de pantalla completa
Sistema de Puntuación
+---------------+---------------+-------------------+
|   Acción      |   Puntos      |   Descripción     |
+---------------+---------------+-------------------+
|  Esquivar     |   +10         | Por obstáculo     |
|  Sobrevivir   |  Tiempo x 1   | Cada segundo vivo |
|  Colisión     |   Game Over   | Fin del juego     |
+---------------+---------------+-------------------+

Flujo del Juego
Menú Principal → Selector de Vehículo → Juego en Progreso
        ↑               ↓                    ↓
        └─────── Game Over ←────── ¿Colisión? ←─┘
                        ↓
                 Reiniciar / Menú / Salir

Cómo Ejecutar
Prerrequisitos

Java JDK 8 o superior

Cualquier IDE (IntelliJ, NetBeans, Eclipse)

Pasos

Clonar o descargar el proyecto

Abrir en tu IDE

Compilar

Ejecutar MenuPrincipal.java

¡Jugar!

Línea de comandos
javac -d bin src/**/*.java
java -cp bin Vista.MenuPrincipal

Estructura del Proyecto
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
│   └── (Factories específicas)
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
<div align="center">
Desarrollador	Código	Rol
Kaleth Molina Díaz	20232020096	Arquitectura y Patrones
Nelson David Molina Ramos	20222020121	Interfaz y Gameplay
</div>
Contacto y Contribuciones

Proyecto académico

Código educativo y demostrativo

Basado en buenas prácticas de diseño de software

<div align="center">

Desarrollado con pasión y código limpio
Universidad Distrital Francisco José de Caldas
Ingeniería de Sistemas
Patrones de Diseño - 2024

</div>
Métricas del Proyecto

6 patrones GoF implementados

15+ clases organizadas por responsabilidad

Interfaz con efectos visuales avanzados

Rendimiento optimizado en Java Swing

Futuras Mejoras

Sistema de power-ups (Observer)

Niveles de dificultad (Strategy)

Guardado de récords (Memento)

Sonidos del juego

Animaciones de transición
