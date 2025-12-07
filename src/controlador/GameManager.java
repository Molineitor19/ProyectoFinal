/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

public class GameManager {
    private static GameManager instance;
    private boolean gameOver = false;
    private int vidas = 1;
    private int score = 0;

    private GameManager() {}

    public static synchronized GameManager getInstance() {
        if (instance == null) instance = new GameManager();
        return instance;
    }

    public boolean isGameOver() { return gameOver; }
    public void setGameOver(boolean v) { gameOver = v; }

    public int getVidas() { return vidas; }
    public void setVidas(int v) { vidas = v; }

    public int getScore() { return score; }
    public void addScore(int s) { score += s; }
}
