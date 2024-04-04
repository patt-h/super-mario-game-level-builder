package Enemies;

import Managers.EnemyManager;

public abstract class Enemy {
    EnemyManager enemyManager;
    public int x, y;
    public int width, height;
    public boolean active;

    public Enemy(int x, int y) {
        enemyManager = new EnemyManager();
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
