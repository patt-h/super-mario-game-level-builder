package com.company;

import Inputs.KeyboardListener;
import Inputs.MouseInputListener;
import Managers.EnemyManager;
import Managers.TileManager;
import Managers.VisualsManager;
import Scenes.Buttons;
import Scenes.Playing;
import Scenes.Tiles;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Editor extends JFrame implements Runnable {
    private Thread editorThread;
    private final int FPS_SET = 144;
    private final int UPS_SET = 200;

    private EditorScreen editorScreen;

    private KeyboardListener keyboardListener;
    private MouseInputListener mouseInputListener;
    private ImageIcon img;

    //CLASSES
    private Camera camera;
    private Playing playing;
    private Tiles tiles;
    private Buttons buttons;
    private EnemyManager enemyManager;
    private TileManager tileManager;
    private VisualsManager visualsManager;

    public static int TILE_SIZE = 48;
    public static int TILES_IN_WIDTH = 20;
    public static int TILES_IN_HEIGHT = 15;
    public static int PLAYING_WIDTH = TILE_SIZE * TILES_IN_WIDTH;
    public static int PLAYING_HEIGHT = TILE_SIZE * TILES_IN_HEIGHT;
    public static int SCREEN_WIDTH = PLAYING_WIDTH + 400;
    public static int SCREEN_HEIGHT = PLAYING_HEIGHT + 50;

    public Editor() {
        initInputs();
        initClasses();
        img = new ImageIcon(Objects.requireNonNull(getClass().getResource("/builder_icon.png")));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Mario Builder | Level " + ConfigWindow.worldName);
        setIconImage(img.getImage());
        setResizable(false);
        setLayout(new FlowLayout());
        add(editorScreen, BorderLayout.WEST);
        editorScreen.add(buttons, BorderLayout.PAGE_END);
        editorScreen.add(tiles, BorderLayout.EAST);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        startEditLoop();
    }

    private void initClasses() {
        editorScreen = new EditorScreen(this);
        enemyManager = new EnemyManager();
        tileManager = new TileManager();
        visualsManager = new VisualsManager();
        camera = new Camera();
        playing = new Playing();
        tiles = new Tiles(tileManager, enemyManager, visualsManager);
        buttons = new Buttons();
    }

    private void initInputs() {
        keyboardListener = new KeyboardListener(this);
        mouseInputListener = new MouseInputListener(this);

        addKeyListener(keyboardListener);
        addMouseListener(mouseInputListener);
        addMouseMotionListener(mouseInputListener);
        requestFocus();
    }

    private void startEditLoop() {
        editorThread = new Thread(this);
        editorThread.start();
    }

    @Override
    public void run() {
        float timePerFrame = 1000000000f / FPS_SET;
        float timePerUpdate = 1000000000f / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.nanoTime();

        float deltaU = 0;
        float deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();
            long elapsedTime = currentTime - previousTime;
            previousTime = currentTime;

            deltaU += elapsedTime / timePerUpdate;
            deltaF += elapsedTime / timePerFrame;

            if (deltaU >= 1) {
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                editorScreen.repaint();
                frames++;
                deltaF--;
            }

            if (System.nanoTime() - lastCheck >= 1000000000L) {
                lastCheck = System.nanoTime();
                frames = 0;
                updates = 0;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void render(Graphics g) {
        playing.render(g, camera.xLvlOffset);
        enemyManager.render(g, camera.xLvlOffset);
        visualsManager.render(g, camera.xLvlOffset);
    }

    public Camera getCamera() {
        return camera;
    }

    public Playing getPlaying() {
        return playing;
    }

    public Tiles getTiles() {
        return tiles;
    }

    public EditorScreen getEditorScreen() {
        return editorScreen;
    }

}
