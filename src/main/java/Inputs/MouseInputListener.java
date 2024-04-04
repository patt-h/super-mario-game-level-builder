package Inputs;

import Enemies.Goomba;
import Enemies.Piranha;
import Enemies.Troopa;
import Visuals.Cloud;
import Visuals.Fence;
import Visuals.FinishLine;
import Visuals.Grass;
import com.company.Editor;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static Enemies.Goomba.GoombaList;
import static Enemies.Piranha.PiranhaList;
import static Enemies.Troopa.TroopaList;
import static Visuals.Cloud.CloudList;
import static Visuals.Fence.FenceList;
import static Visuals.FinishLine.FinishLineList;
import static Visuals.Grass.GrassList;

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
        if (e.getX() < Editor.PLAYING_WIDTH && e.getY() < Editor.PLAYING_HEIGHT + 35) {
            // Tiles
            if (editor.getTiles().selectedIndex == 0) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    editor.getPlaying().lvl[gridY][gridX] = editor.getTiles().getId();
                }
                else if (e.getButton() == MouseEvent.BUTTON3) {
                    editor.getPlaying().lvl[gridY][gridX] = 90;
                }
            }
            // Visuals
            else if (editor.getTiles().selectedIndex == 1) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    switch (editor.getTiles().id) {
                        case 0 -> GrassList.add(new Grass(gridX * 48, gridY * 48));
                        case 1 -> CloudList.add(new Cloud(gridX * 48, gridY * 48));
                        case 2 -> FenceList.add(new Fence(gridX * 48, gridY * 48));
                        case 3 -> FinishLineList.add(new FinishLine(gridX * 48, (gridY * 48) + 2 - 384));
                    }
                }
                else if (e.getButton() == MouseEvent.BUTTON3) {
                    switch (editor.getTiles().id) {
                        case 0 -> {
                            for (int i = 0; i < GrassList.size(); i++) {
                                Grass grass = GrassList.get(i);
                                if (grass.getX() == gridX * 48 && grass.getY() == gridY * 48) {
                                    GrassList.remove(i);
                                }
                            }
                        }
                        case 1 -> {
                            for (int i = 0; i < CloudList.size(); i++) {
                                Cloud cloud = CloudList.get(i);
                                if (cloud.getX() == gridX * 48 && cloud.getY() == gridY * 48) {
                                    CloudList.remove(i);
                                }
                            }
                        }
                        case 2 -> {
                            for (int i = 0; i < FenceList.size(); i++) {
                                Fence fence = FenceList.get(i);
                                if (fence.getX() == gridX * 48 && fence.getY() == gridY * 48) {
                                    FenceList.remove(i);
                                }
                            }
                        }
                        case 3 -> {
                            for (int i = 0; i < FinishLineList.size(); i++) {
                                FinishLine finishLine = FinishLineList.get(i);
                                if (finishLine.getX() == gridX * 48 && finishLine.getY() == (gridY * 48) + 2 - 384) {
                                    FinishLineList.remove(i);
                                }
                            }
                        }
                    }
                }
            }
            // Mobs
            else if (editor.getTiles().selectedIndex == 2) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    switch (editor.getTiles().id) {
                        case 0 -> GoombaList.add(new Goomba(gridX * 48, gridY * 48 - 1));
                        case 2 -> TroopaList.add(new Troopa(gridX * 48, (gridY * 48) - 1));
                        case 3 -> {
                            PiranhaList.add(new Piranha((gridX * 48) + 24, (gridY * 48) + 10));
                            System.out.println((gridY * 48));
                        }
                    }
                }
                else if (e.getButton() == MouseEvent.BUTTON3) {
                    switch (editor.getTiles().id) {
                        case 0 -> {
                            for (int i = 0; i < GoombaList.size(); i++) {
                                Goomba goomba = GoombaList.get(i);
                                if (goomba.getX() == gridX * 48 && goomba.getY() == gridY * 48 - 1) {
                                    GoombaList.remove(i);
                                }
                            }
                        }
                        case 2 -> {
                            for (int i = 0; i < TroopaList.size(); i++) {
                                Troopa troopa = TroopaList.get(i);
                                if (troopa.getX() == gridX * 48 && troopa.getY() == (gridY * 48) - 1) {
                                    TroopaList.remove(i);
                                }
                            }
                        }
                        case 3 -> {
                            for (int i = 0; i < PiranhaList.size(); i++) {
                                Piranha piranha = PiranhaList.get(i);
                                if (piranha.getX() == (gridX * 48) + 24 && piranha.getY() == (gridY * 48) + 10) {
                                    PiranhaList.remove(i);
                                }
                            }
                        }
                    }
                }
            }
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
        if (e.getX() < Editor.PLAYING_WIDTH && e.getY() < Editor.PLAYING_HEIGHT + 35) {
            if (isDragging) {
                int mouseX = e.getX() + editor.getCamera().xLvlOffset - 13;
                int mouseY = e.getY() - 36;
                int gridX = mouseX / 48;
                int gridY = mouseY / 48;
                if (editor.getTiles().selectedIndex == 0) {
                    if (lastButton == MouseEvent.BUTTON1) {
                        editor.getPlaying().lvl[gridY][gridX] = editor.getTiles().getId();
                    } else if (lastButton == MouseEvent.BUTTON3) {
                        editor.getPlaying().lvl[gridY][gridX] = 90;
                    }
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
