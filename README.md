
# No Choques - Videojuego de Esquivar Obstáculos

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/Java%20Swing-FF9800?style=flat&logo=java&logoColor=white)
![Design Patterns](https://img.shields.io/badge/Design%20Patterns-GoF-blueviolet?style=flat)


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

```
Administra puntuación, vidas y estado general.

Observer – Sistema de Notificaciones

Comunicación desacoplada entre componentes.

```java
gameManager.agregarObservador(vista);
gameManager.notificar("puntos", 100);

```
Actualiza la interfaz en tiempo real.

Strategy – Comportamiento de Enemigos

Permite intercambiar algoritmos de movimiento.

```java
interface EnemyMovementStrategy {
    void mover();
}
```
Estrategias incluidas:

Movimiento recto

ZigZag

Decorator – Mejoras de Vehículos

Extiende comportamiento sin modificar la clase base.
