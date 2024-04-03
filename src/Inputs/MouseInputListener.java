package Inputs;

import com.company.Editor;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputListener implements MouseListener, MouseMotionListener {
    Editor editor;
    int lastButton;
    boolean isDragging = false;

    public MouseInputListener(Editor editor) {
        this.editor = editor;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX() + editor.getCamera().xLvlOffset - 13;
        int mouseY = e.getY() - 36;
        int gridX = mouseX / 48;
        int gridY = mouseY / 48;
        if (e.getButton() == MouseEvent.BUTTON1) {
            editor.getPlaying().lvl[gridY][gridX] = editor.getTiles().getId();
        }
        else if (e.getButton() == MouseEvent.BUTTON3) {
            editor.getPlaying().lvl[gridY][gridX] = 90;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        isDragging = true;
        lastButton = e.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isDragging = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {
        if (isDragging) {
            int mouseX = e.getX() + editor.getCamera().xLvlOffset - 13;
            int mouseY = e.getY() - 36;
            int gridX = mouseX / 48;
            int gridY = mouseY / 48;
            if (lastButton == MouseEvent.BUTTON1) {
                editor.getPlaying().lvl[gridY][gridX] = editor.getTiles().getId();
            }
            else if (lastButton == MouseEvent.BUTTON3) {
                editor.getPlaying().lvl[gridY][gridX] = 90;
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
