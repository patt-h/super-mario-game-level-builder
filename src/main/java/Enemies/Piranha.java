package Enemies;

import java.util.ArrayList;

public class Piranha extends Enemy {
    public static ArrayList<Piranha> PiranhaList = new ArrayList<>();

    public Piranha(int x, int y) {
        super(x, y);
        width = 120;
        height = 120;
    }
}
