package com.gmail.greiling_rn.untitledTopDown.io;

import com.gmail.greiling_rn.untitledTopDown.Main;
import com.gmail.greiling_rn.untitledTopDown.utils.Vector2;

import java.awt.event.*;

//todo think of splitting class
public class Input implements KeyListener, MouseListener, MouseWheelListener, MouseMotionListener {

    public static final int[] inputIDInputCodes = {KeyEvent.VK_W + 1, KeyEvent.VK_A + 1, KeyEvent.VK_S + 1,
            KeyEvent.VK_D + 1, -1 - MouseEvent.BUTTON1, KeyEvent.VK_R + 1};

    private static final float[] inputDownTimes = {-1, -1, -1, -1, -1, -1};
    public static Vector2 lastMouseLocationOnPanel = new Vector2();

    public static float getKeyDownTime(InputType in) {
        return inputDownTimes[in.ID];
    }

    public static boolean isKeyDown(InputType i) {
        return -1 != getKeyDownTime(i);
    }
    
    public static void update(float dt) {
        for (int i = 0;i < inputDownTimes.length;i++) {
            if (inputDownTimes[i] != -1) {
                inputDownTimes[i] += dt;
            }
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        for (int i = 0;i < inputIDInputCodes.length;i++) {
            if (inputIDInputCodes[i] > 0) {
                if (inputIDInputCodes[i] - 1 == e.getKeyCode()) {
                    inputDownTimes[i] = 0; //todo Check for possible bugs
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (int i = 0;i < inputIDInputCodes.length;i++) {
            if (inputIDInputCodes[i] > 0) {
                if (inputIDInputCodes[i] - 1 == e.getKeyCode()) {
                    inputDownTimes[i] = -1;
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        for (int i = 0;i < inputIDInputCodes.length;i++) {
            if (inputIDInputCodes[i] < 0) {
                if (-(inputIDInputCodes[i] + 1) == e.getButton()) {
                    inputDownTimes[i] = 0; //todo Check for possible bugs
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (int i = 0;i < inputIDInputCodes.length;i++) {
            if (inputIDInputCodes[i] < 0) {
                if (-(inputIDInputCodes[i] + 1) == e.getButton()) {
                    inputDownTimes[i] = -1;
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override //todo pause on mouse exit
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        lastMouseLocationOnPanel = Vector2.fromPoint(Main.instance.jp.getMousePosition());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        lastMouseLocationOnPanel = Vector2.fromPoint(Main.instance.jp.getMousePosition());
    }

    @Override //todo add support for mouse wheel input
    public void mouseWheelMoved(MouseWheelEvent e) {

    }

    public enum InputType {
        MOVE_UP(0), MOVE_LEFT(1), MOVE_DOWN(2), MOVE_RIGHT(3), ATTACK(4), RELOAD(5);

        final int ID;

        InputType(int id) {
            this.ID = id;
        }
    }
}