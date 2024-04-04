package Visuals;

import java.util.ArrayList;

public class FinishLine extends Visuals {
    public static ArrayList<FinishLine> FinishLineList = new ArrayList<>();

    public FinishLine(int x, int y) {
        super(x, y);
        width = 382;
        height = 430;
        loadFrames(255, "finishLine.png");
    }
}
