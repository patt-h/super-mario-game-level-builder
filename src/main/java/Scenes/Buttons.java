package Scenes;

import Utilities.SaveLevel;
import com.company.Editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttons extends JPanel {
    JButton save;
    JButton settings;
    JButton clear;

    public Buttons() {
        initButtonSave();
        initButtonSettings();
        initButtonClear();
        setLayout(new FlowLayout(FlowLayout.TRAILING, 20, 8));
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        setPreferredSize(new Dimension(Editor.SCREEN_WIDTH - 375, Editor.SCREEN_HEIGHT - Editor.PLAYING_HEIGHT));
        add(clear);
        add(settings);
        add(save);
    }

    private void initButtonSave() {
        save = new JButton("Save map");
        save.setFocusable(false);
        save.setPreferredSize(new Dimension(100,40));
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveLevel.saveJson();
            }
        });
    }

    private void initButtonSettings() {
        settings = new JButton("Settings");
        settings.setFocusable(false);
        settings.setPreferredSize(new Dimension(100,40));
    }

    private void initButtonClear() {
        clear = new JButton("Clear map");
        clear.setFocusable(false);
        clear.setPreferredSize(new Dimension(100,40));
    }
}
