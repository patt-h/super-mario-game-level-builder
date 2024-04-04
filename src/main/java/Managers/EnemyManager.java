package Managers;

import Enemies.Goomba;
import Enemies.Piranha;
import Enemies.Troopa;
import Utilities.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static Enemies.Goomba.GoombaList;
import static Enemies.Piranha.PiranhaList;
import static Enemies.Troopa.TroopaList;

public class EnemyManager {

    public BufferedImage atlas;
    public ArrayList<BufferedImage> sprites = new ArrayList<>();

    public EnemyManager() {
        loadAtlas();
        loadSprites();
    }

    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas(LoadSave.ENEMY_ATLAS);
    }

    public void loadSprites() {
        for (int j = 0; j < 11; j++) {
            for (int i = 0; i < 10; i++) {
                sprites.add(atlas.getSubimage(i * 40, j * 40, 40, 40));
            }
        }
    }

    public void render(Graphics g, int xLvlOffset) {
        for (Goomba go: GoombaList) {
            g.drawImage(sprites.get(0), go.x - xLvlOffset, go.y, go.width, go.height, null);
        }
        for (Piranha pi : PiranhaList) {
            g.drawImage(sprites.get(3), pi.x - xLvlOffset, pi.y, pi.width, pi.height, null);
        }
        for (Troopa tr : TroopaList) {
            g.drawImage(sprites.get(2), tr.x - xLvlOffset, tr.y - 20, tr.width, tr.height, null);
        }
    }
}
