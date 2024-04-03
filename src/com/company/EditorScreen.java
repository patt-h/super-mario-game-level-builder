package com.company;

import javax.swing.*;
import java.awt.*;

public class EditorScreen extends JPanel {
    private Editor editor;

    public EditorScreen(Editor editor) {
        this.editor = editor;
        setLayout(new BorderLayout());
        setPanelSize();
    }

    private void setPanelSize() {
        Dimension size = new Dimension(editor.SCREEN_WIDTH,editor.SCREEN_HEIGHT);
        setPreferredSize(size);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        editor.render(g);
        repaint();
    }
}
