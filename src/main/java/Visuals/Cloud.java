package Visuals;

import java.util.ArrayList;

public class Cloud extends Visuals {
    public static ArrayList<Cloud> CloudList = new ArrayList<>();

    public Cloud(int x, int y) {
        super(x, y);
        width = 97;
        height = 72;
        loadFrames(65, "Cloud.png");
    }
}
