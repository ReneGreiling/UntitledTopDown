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
    private boolean canRepaint = false;

    public JFrame jfr;
    public JPanel jp;

    public static void main(String[] args) {
        new Main();
        instance.start();
    }

    public Main() {
        instance = this;
        jfr = new JFrame();
        jfr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jp = new JPanel(){
            @Override
            public void repaint() {
                canRepaint = false;
                super.repaint();
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                draw(g);
                canRepaint = true;
            }
        };
        jfr.setExtendedState(Frame.MAXIMIZED_BOTH);

        // FIXME: 05.03.2022 Only fullscreen


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

    public void start() {
        Thread t = new Thread(() -> {
            long time;
            float dt = 0;
            while (running) {
                try {
                    time = System.currentTimeMillis();
                    update(dt);
                    //todo think of a better way to wait for repaint
                    jp.repaint();
                    while (!canRepaint) {
                        Thread.sleep(0, 1);
                    }
                    time = System.currentTimeMillis() - time;
                    time++;
                    dt = (float) (time / 1000.0);
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    running = false;
                }
            }
            System.exit(0);
        });
        t.start();
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