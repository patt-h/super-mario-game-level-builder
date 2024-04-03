package Managers;

import Helpz.LoadSave;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager {

    public BufferedImage atlas;
    public ArrayList<BufferedImage> sprites = new ArrayList<>();

    public TileManager() {
        loadAtlas();
        loadSprites();
    }

    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas();
    }

    public void loadSprites() {
        for (int y = 0; y < 28; y++) {
            for (int x = 0; x < 38; x++) {
                sprites.add(atlas.getSubimage((x*16)+x+1,(y*16)+y+1,16,16));
            }
        }
    }
}
