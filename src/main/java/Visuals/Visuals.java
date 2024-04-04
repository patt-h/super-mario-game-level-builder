package Visuals;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public abstract class Visuals {
    public int x, y;
    public int width, height;

    public BufferedImage[] frames;

    public Visuals(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected void loadFrames(int frameLength, String filename) {
        try {
            frames = new BufferedImage[1];
            URL imageUrl = getClass().getResource(filename);
            BufferedImage spriteSheet = ImageIO.read(imageUrl);
            int frameHeight = spriteSheet.getHeight();
            frames[0] = spriteSheet.getSubimage(0, 0, frameLength, frameHeight);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
