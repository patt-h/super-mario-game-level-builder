package Managers;

import Utilities.LoadSave;
import Visuals.Cloud;
import Visuals.Fence;
import Visuals.FinishLine;
import Visuals.Grass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static Visuals.Cloud.CloudList;
import static Visuals.Fence.FenceList;
import static Visuals.FinishLine.FinishLineList;
import static Visuals.Grass.GrassList;

public class VisualsManager {

    public BufferedImage atlas;
    public ArrayList<BufferedImage> sprites = new ArrayList<>();

    public VisualsManager() {
        loadAtlas();
        loadSprites();
    }

    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas(LoadSave.VISUALS_ATLAS);
    }

    public void loadSprites() {
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 5; i++) {
                sprites.add(atlas.getSubimage(i * 128, j * 128, 128, 128));
            }
        }
    }

    public void render(Graphics g, int xLvlOffset) {
        for (Grass gr : GrassList) {
            g.drawImage(gr.frames[0], gr.x - xLvlOffset, gr.y, gr.width, gr.height, null);
        }
        for (Cloud c : CloudList) {
            g.drawImage(c.frames[0], c.x - xLvlOffset, c.y, c.width, c.height, null);
        }
        for (Fence f : FenceList) {
            g.drawImage(f.frames[0], f.x - xLvlOffset, f.y, f.width, f.height, null);
        }
        for (FinishLine fl : FinishLineList) {
            g.drawImage(fl.frames[0], fl.x - xLvlOffset, fl.y, fl.width, fl.height, null);
        }
    }
}
