package Inputs;

import com.company.Editor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyboardListener implements KeyListener {
    Editor editor;

    public KeyboardListener(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_D -> {
                editor.getCamera().checkCloseToBorder();
                editor.getCamera().right = true;
                editor.getCamera().setPosition();
            }
            case KeyEvent.VK_A -> {
                editor.getCamera().checkCloseToBorder();
                editor.getCamera().left = true;
                editor.getCamera().setPosition();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_D -> {
                editor.getCamera().right = false;
            }
            case KeyEvent.VK_A-> {
                editor.getCamera().left = false;
            }
        }
    }
}
