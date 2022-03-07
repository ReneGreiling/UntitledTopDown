package com.gmail.greiling_rn.untitledTopDown;

import com.gmail.greiling_rn.untitledTopDown.gm.GameManager;
import com.gmail.greiling_rn.untitledTopDown.gm.Manager;
import com.gmail.greiling_rn.untitledTopDown.io.Input;

import javax.swing.*;
import java.awt.*;

public class Main implements Manager {

    public static Main instance;
    private static boolean running = true;
    public Manager manager = new GameManager();

    private long time;
    private float dt = 0;

    public JFrame jfr;
    public JPanel jp;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        instance = this;
        jfr = new JFrame();
        jfr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jp = new JPanel(){
            @Override
            public void repaint() {
                time = System.currentTimeMillis();
                Main.instance.update(dt);
                super.repaint();
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                draw(g);
                time = System.currentTimeMillis() - time;
                dt = (float) (time / 1000.0);
                if (running)
                    repaint();
                else {
                    System.exit(0);
                }
            }
        };
        jfr.setExtendedState(Frame.MAXIMIZED_BOTH);

        // todo Only fullscreen 1920x1080


        jp.setBounds(0, 0, 1920, 1080); //todo
        jfr.add(jp);
        Input in = new Input();
        jfr.addMouseListener(in);
        jfr.addMouseMotionListener(in);
        jfr.addKeyListener(in);
        jfr.addMouseWheelListener(in);


        jfr.setUndecorated(true);
        jfr.setVisible(true);
    }

    public void exit() {
        running = false;
    }

    public void draw(Graphics g) {
        if (manager != null)
            manager.draw(g);
    }

    public void update(float dt) {
        if (manager != null)
            manager.update(dt);
        Input.update(dt);
    }
}