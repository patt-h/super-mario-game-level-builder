package Enemies;

import java.util.ArrayList;

public class Goomba extends Enemy {
    public static ArrayList<Goomba> GoombaList = new ArrayList<>();

    public Goomba(int x, int y) {
        super(x, y);
        width = 120;
        height = 120;
    }
}
