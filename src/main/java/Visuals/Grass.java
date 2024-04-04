package Visuals;

import java.util.ArrayList;

public class Grass extends Visuals {
    public static ArrayList<Grass> GrassList = new ArrayList<>();

    public Grass(int x, int y) {
        super(x, y);
        width = 96;
        height = 48;
        loadFrames(32, "Grass.png");
    }
}
