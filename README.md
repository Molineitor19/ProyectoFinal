# No Choques - Videojuego de Esquivar Obstáculos

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/Java%20Swing-FF9800?style=flat&logo=java&logoColor=white)
![Design Patterns](https://img.shields.io/badge/Design%20Patterns-GoF-blueviolet?style=flat)

**Un juego de destreza y reflejos desarrollado en Java.**

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
- Menú principal con efectos visuales
- Selector de vehículos interactivo
- Sistema de puntuación en tiempo real
- Diálogos personalizados
- Modo pantalla completa opcional

### Sistema de Control Dual
- **Mouse:** movimiento vertical
- **Teclado:** flechas de dirección
- **M:** cambiar entre modos

### Sistema de Vehículos

| Vehículo | Variante | Velocidad | Característica |
|---------|----------|-----------|----------------|
| Carro   | Normal   | Media     | Estable, grande |
| Carro   | Pro      | Alta      | Mejor aceleración |
| Moto    | Normal   | Alta      | Ágil, pequeña |
| Moto    | Pro      | Máxima    | Muy rápida |
| Bici    | Normal   | Baja      | Muy maniobrable |
| Bici    | Pro      | Media     | Balance perfecto |

### Sistema de Progresión
- Puntos por obstáculos esquivados
- 1 vida
- Puntuación persistente por sesión
- Pantalla de Game Over

---

## Arquitectura y Patrones

### Patrones Implementados

#### **Singleton – GameManager**
Control centralizado del juego:

```java
GameManager.getInstance();

Observer – Notificaciones

gameManager.agregarObservador(vista);
gameManager.notificar("puntos", 100);

Strategy – Movimiento de Enemigos

interface EnemyMovementStrategy { void mover(); }

Factory Method – Creación de Vehículos

VehiculoFactory factory = new CarroFactory();

Facade – Manejo de Recursos

Image img = ImageFacade.cargar("/ruta.png");

Patrones No Implementados
Patrón	Motivo
Builder	No hay construcción compleja
Composite	No hay jerarquías
State	Estados simples
Command	Acciones simples
Memento	No hay guardado
Visitor	No se requiere
