package Scenes;

import Managers.EnemyManager;
import Managers.TileManager;
import Managers.VisualsManager;
import com.company.Editor;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Tiles extends JTabbedPane {
    JPanel tilesPanel;
    JPanel visualsPanel;
    JPanel mobsPanel;
    private JScrollPane tilesScrollPane;
    private JScrollPane visualsScrollPane;
    private JScrollPane mobsScrollPane;

    public int id;
    public int selectedIndex;

    TileManager tileManager;
    EnemyManager enemyManager;
    VisualsManager visualsManager;
    private Map<JLabel, Integer> labelIds;
    private Map<JLabel, Integer> mobsIds;
    private Map<JLabel, Integer> visualsIds;

    public Tiles(TileManager tileManager, EnemyManager enemyManager, VisualsManager visualsManager) {
        this.tileManager = tileManager;
        this.enemyManager = enemyManager;
        this.visualsManager = visualsManager;
        setFocusable(false);
        addChangeListener(this::stateChanged);
        initTilesPane();
        initVisualsPane();
        initMobsPane();
        setPreferredSize(new Dimension(375, Editor.SCREEN_HEIGHT));
        add("Tiles", tilesScrollPane);
        add("Visuals", visualsPanel);
        add("Mobs", mobsScrollPane);
    }

    private void initTilesPane() {
        tilesPanel = new JPanel();
        tilesPanel.setPreferredSize(new Dimension(340, 3650));
        tilesPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 10, 10));
        tilesScrollPane = new JScrollPane(tilesPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        labelIds = new HashMap<>();

        for (int i = 0; i < tileManager.sprites.size(); i++) {
            BufferedImage sprite = tileManager.sprites.get(i);
            ImageIcon icon = new ImageIcon(sprite);
            Image scaledImage = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JLabel label = new JLabel(scaledIcon);
            labelIds.put(label, i);
            label.addMouseListener(new MyMouseListener());
            tilesPanel.add(label);
        }
    }

    private void initVisualsPane() {
        visualsPanel = new JPanel();
        visualsPanel.setPreferredSize(new Dimension(340, 3650));
        visualsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 10, 10));
        visualsScrollPane = new JScrollPane(visualsPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        visualsIds= new HashMap<>();

        for (int i = 0; i < visualsManager.sprites.size(); i++) {
            BufferedImage sprite = visualsManager.sprites.get(i);
            ImageIcon icon = new ImageIcon(sprite);
            Image scaledImage = icon.getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JLabel label = new JLabel(scaledIcon);
            visualsIds.put(label, i);
            label.addMouseListener(new MyMouseListener());
            visualsPanel.add(label);
        }
    }

    private void initMobsPane() {
        mobsPanel = new JPanel();
        mobsPanel.setPreferredSize(new Dimension(340, 1000));
        mobsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 10, 10));
        mobsScrollPane = new JScrollPane(mobsPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mobsIds = new HashMap<>();

        for (int i = 0; i < enemyManager.sprites.size(); i++) {
            BufferedImage sprite = enemyManager.sprites.get(i);
            ImageIcon icon = new ImageIcon(sprite);
            Image scaledImage = icon.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JLabel label = new JLabel(scaledIcon);
            mobsIds.put(label, i);
            label.addMouseListener(new MyMouseListener());
            mobsPanel.add(label);
        }
    }

    private class MyMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel clickedLabel = (JLabel) e.getSource();
            if (selectedIndex == 0) {
                id = labelIds.get(clickedLabel);
            }
            else if (selectedIndex == 1) {
                id = visualsIds.get(clickedLabel);
            }
            else if (selectedIndex == 2) {
                id = mobsIds.get(clickedLabel);
            }
            System.out.println("ID labelu: " + id);
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }

    public void stateChanged(ChangeEvent e) {
        selectedIndex = this.getSelectedIndex();
    }

    public int getId() {
        return id;
    }
}
