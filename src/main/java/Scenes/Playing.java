package Scenes;

import Utilities.LevelBuilder;
import Managers.TileManager;
import com.company.ConfigWindow;
import com.company.Editor;

import java.awt.*;


public class Playing {
    public static int[][] lvl;
    private TileManager tileManager;

    public Playing() {
        if (ConfigWindow.newWorld) {
            lvl = LevelBuilder.getLevelData();
        }
        else {
            lvl = LevelBuilder.getLevelDataFromFile(ConfigWindow.worldName);
        }
        tileManager = new TileManager();
    }


    public void render(Graphics g, int LvlOffset) {
        for (int i = 0; i < lvl.length; i++) {
            for (int j = 0; j < lvl[i].length; j++) {
                int id = lvl[i][j];
                if (j * 48 - LvlOffset < Editor.PLAYING_WIDTH && j * 48 - LvlOffset + Editor.TILE_SIZE > 0) {
                    g.drawImage(tileManager.sprites.get(id), j * 48 - LvlOffset, i * 48, 48, 48, null);
                }
            }
        }

        // Grid
        for (int y = 0; y < Editor.PLAYING_HEIGHT; y+=48) {
            for (int x = 0; x < Editor.PLAYING_WIDTH; x+= 48) {
                g.setColor(Color.GRAY);
                g.drawRect(x,y,48,48);
            }
        }
    }
}
