package Enemies;

import java.util.ArrayList;

public class Troopa extends Enemy {
    public static ArrayList<Troopa> TroopaList = new ArrayList<>();

    public Troopa(int x, int y) {
        super(x, y);
        width = 120;
        height = 120;
    }
}
