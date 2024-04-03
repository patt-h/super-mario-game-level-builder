package Scenes;

import Managers.TileManager;
import com.company.Editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Tiles extends JTabbedPane {
    JPanel tilesPane;
    JPanel visualsPanel;
    JPanel mobsPanel;
    private JScrollPane scrollPane;
    public int id;

    TileManager tileManager;
    private Map<JLabel, Integer> labelIds;

    public Tiles() {
        tileManager = new TileManager();
        setFocusable(false);
        initTilesPane();
        initVisualsPane();
        initMobsPane();
        setPreferredSize(new Dimension(375, Editor.SCREEN_HEIGHT));
        add("Tiles", scrollPane);
        add("Visuals", visualsPanel);
        add("Mobs", mobsPanel);
    }

    private void initTilesPane() {
        tilesPane = new JPanel();
        tilesPane.setPreferredSize(new Dimension(340, 3650));
        tilesPane.setLayout(new FlowLayout(FlowLayout.TRAILING, 10, 10));
        scrollPane = new JScrollPane(tilesPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        labelIds = new HashMap<>();

        for (int i = 0; i < tileManager.sprites.size(); i++) {
            BufferedImage sprite = tileManager.sprites.get(i);
            ImageIcon icon = new ImageIcon(sprite);
            Image scaledImage = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JLabel label = new JLabel(scaledIcon);
            labelIds.put(label, i);
            label.addMouseListener(new MyMouseListener());
            tilesPane.add(label);
        }
    }

    private void initVisualsPane() {
        visualsPanel = new JPanel();
    }

    private void initMobsPane() {
        mobsPanel = new JPanel();
    }

    private class MyMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel clickedLabel = (JLabel) e.getSource();
            id = labelIds.get(clickedLabel);
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

    public int getId() {
        return id;
    }
}
