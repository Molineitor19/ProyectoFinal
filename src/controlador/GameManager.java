package controlador;

import observador.SujetoObservable;

public class GameManager extends SujetoObservable {
    private static GameManager instance;
    private boolean gameOver = false;
    private int vidas = 1;
    private int score = 0;
    private int puntosPorEvasion = 10; // Puntos por esquivar un enemigo

    private GameManager() {}

    public static synchronized GameManager getInstance() {
        if (instance == null) instance = new GameManager();
        return instance;
    }

    public boolean isGameOver() { return gameOver; }
    public void setGameOver(boolean v) { 
        gameOver = v; 
        notificar("gameOver", v);
    }

    public int getVidas() { return vidas; }
    public void setVidas(int v) { 
        vidas = v; 
        notificar("vidas", v);
    }

    public int getScore() { return score; }
    
    public void addScore(int s) { 
        score += s; 
        notificar("puntos", score); // ¡Notifica el cambio!
    }
    
    public void resetScore() {
        score = 0;
        notificar("puntos", score);
    }
    
    public void sumarPuntosPorEvasion() {
        addScore(puntosPorEvasion);
    }
    
    // Método para cuando el jugador esquiva un enemigo
    public void enemigoEsquivado() {
        sumarPuntosPorEvasion();
    }
}