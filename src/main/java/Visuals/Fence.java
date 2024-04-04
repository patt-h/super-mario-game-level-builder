package Visuals;

import java.util.ArrayList;

public class Fence extends Visuals {
    public static ArrayList<Fence> FenceList = new ArrayList<>();

    public Fence(int x, int y) {
        super(x, y);
        width = 144;
        height = 48;
        loadFrames(96, "Fence.png");
    }
}
