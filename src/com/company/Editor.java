package com.company;

import Inputs.KeyboardListener;
import Inputs.MouseInputListener;
import Scenes.Playing;
import Scenes.Tiles;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Editor extends JFrame {
    private EditorScreen editorScreen;

    private KeyboardListener keyboardListener;
    private MouseInputListener mouseInputListener;
    private ImageIcon img;

    //CLASSES
    private Camera camera;
    private Playing playing;
    private Tiles tiles;

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

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newFile = new JMenuItem("New");
        JMenuItem loadFile = new JMenuItem("Load");
        JMenuItem saveFile = new JMenuItem("Save");
        fileMenu.add(newFile);
        fileMenu.add(loadFile);
        fileMenu.add(saveFile);

        JMenu mapMenu = new JMenu("Map");
        JMenuItem changeSize = new JMenuItem("Change map size");
        JMenuItem clear = new JMenuItem("Clear map");
        mapMenu.add(changeSize);
        mapMenu.add(clear);

        menuBar.add(fileMenu);
        menuBar.add(mapMenu);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Mario Builder");
        setIconImage(img.getImage());
        setResizable(false);
        setLayout(new FlowLayout());
        add(editorScreen, BorderLayout.WEST);
        editorScreen.add(tiles, BorderLayout.EAST);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initClasses() {
        editorScreen = new EditorScreen(this);
        camera = new Camera();
        playing = new Playing();
        tiles = new Tiles();
    }

    private void initInputs() {
        keyboardListener = new KeyboardListener(this);
        mouseInputListener = new MouseInputListener(this);

        addKeyListener(keyboardListener);
        addMouseListener(mouseInputListener);
        addMouseMotionListener(mouseInputListener);
        requestFocus();
    }

    public void render(Graphics g) {
        playing.render(g, camera.xLvlOffset);
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
