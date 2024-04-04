package com.company;

import Utilities.LevelBuilder;

import static Utilities.LevelBuilder.getLevelDataFromFile;

public class Camera {
    public int[][] lvl;
    public int xLvlOffset;
    public int lvlTilesWide;
    private int maxTilesOffset;
    private int maxLvlOffsetX;

    public int x = 528;
    public boolean left;
    public boolean right;

    public Camera() {
        if (ConfigWindow.newWorld) {
            lvl = LevelBuilder.getLevelData();
        }
        else {
            lvl = getLevelDataFromFile(ConfigWindow.worldName);
        }

        initBorderDistance();
    }

    public void initBorderDistance() {
        lvlTilesWide = lvl[0].length;
        maxTilesOffset = lvlTilesWide - Editor.TILES_IN_WIDTH;
        maxLvlOffsetX = maxTilesOffset * Editor.TILE_SIZE;
    }

    public void checkCloseToBorder() {
        int diff = x - xLvlOffset;
        int leftBorder = (int) (0.5 * Editor.PLAYING_WIDTH);
        int rightBorder = (int) (0.5 * Editor.PLAYING_WIDTH);
        if (diff > rightBorder) {
            xLvlOffset += diff - rightBorder;
        } else if (diff < leftBorder) {
            xLvlOffset += diff - leftBorder;
        }

        if (xLvlOffset > maxLvlOffsetX) {
            xLvlOffset = maxLvlOffsetX;
        } else if (xLvlOffset < 0) {
            xLvlOffset = 0;
        }
    }

    public void setPosition() {
        if (left && x > 0) {
            x -= 48;
        }
        if (right && x < (lvlTilesWide * 48) - (10 * 48)) {
            x += 48;
        }
    }
}
