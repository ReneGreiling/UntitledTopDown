package me.rene.x.gm;

import me.rene.x.entities.Dummy;
import me.rene.x.entities.Player;
import me.rene.x.events.EntityDamageByEntityEvent;
import me.rene.x.events.EventManager;
import me.rene.x.io.Input;
import me.rene.x.particle.Particle;
import me.rene.x.particle.TextParticle;
import me.rene.x.utils.Camera;
import me.rene.x.utils.Vector2;

import java.awt.*;
import java.util.ArrayList;

public class GameManager implements Manager {

    ArrayList<Particle> particle = new ArrayList<>();

    Dummy dummy = new Dummy(new Vector2(8 - .3, 2));
    
    Player player = new Player(new Vector2(),10);
    Camera camera = new Camera(new Vector2(), 1920./16);



    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);

        g.fillRect((int) camera.getLocationOnPanel(dummy.getLocation()).x,
                (int) camera.getLocationOnPanel(dummy.getLocation()).y,
                (int) camera.getLocationOnPanel(dummy.getSize()).x,
                (int) camera.getLocationOnPanel(dummy.getSize()).y);

        g.setColor(Color.BLACK);

        g.fillRect((int) camera.getLocationOnPanel(player.getLocation()).x,
                (int) camera.getLocationOnPanel(player.getLocation()).y,
                (int) camera.getLocationOnPanel(player.getSize()).x,
                (int) camera.getLocationOnPanel(player.getSize()).y);

        g.setColor(Color.RED);

        Vector2 playerLocOnPanel = camera.getLocationOnPanel(player.getLocation());
        Vector2 playerSizeOnScreen = camera.getLocationOnPanel(player.getSize());

        g.drawRect((int) playerLocOnPanel.x, (int) playerLocOnPanel.y, (int) playerSizeOnScreen.x,
                (int) playerSizeOnScreen.y);

        Vector2 aimDirLinePoint2 = playerLocOnPanel.add(playerSizeOnScreen.divide(2))
                .add(camera.getLocationOnPanel(Input.lastMouseLocationOnPanel.subtract(playerLocOnPanel
                        .add(playerSizeOnScreen.divide(2))).normalise().divide(2)));

        g.drawLine((int) (playerLocOnPanel.x + playerSizeOnScreen.x / 2),
                ((int) (playerLocOnPanel.y + playerSizeOnScreen.y / 2)),
                (int) aimDirLinePoint2.x, (int) aimDirLinePoint2.y);

        particle.forEach(p -> {
            if (p instanceof TextParticle) {
                TextParticle tp = (TextParticle) p;
                g.setColor(tp.getColor());
                g.setFont(new Font("Arial", Font.BOLD, (int) (camera.getSize() * .2)));
                g.drawString(tp.getText(), (int) camera.getLocationOnPanel(tp.getLocation()).x,
                        (int) camera.getLocationOnPanel(tp.getLocation()).y);
            }
        });
    }

    @Override
    public void update(float dt) {
        Vector2 v = new Vector2();

        if (Input.isKeyDown(Input.InputType.MOVE_UP)) {
            v = v.subtract(new Vector2(0, 1));
        }

        if (Input.isKeyDown(Input.InputType.MOVE_LEFT)) {
            v = v.subtract(new Vector2(1, 0));
        }

        if (Input.isKeyDown(Input.InputType.MOVE_DOWN)) {
            v = v.add(new Vector2(0, 1));
        }

        if (Input.isKeyDown(Input.InputType.MOVE_RIGHT)) {
            v = v.add(new Vector2(1, 0));
        }

        ArrayList<Particle> toRemove = new ArrayList<>();
        particle.forEach(p -> {
            p.lowerRemainingTimeVisibleBy(dt);
            if (p.getRemainingTimeVisible() < 0) {
                toRemove.add(p);
            }
        });
        particle.removeAll(toRemove);
        toRemove.clear();

        v = v.normalise().multiply(dt*10);

        player.setLocation(player.getLocation().add(v));
    }

    public void addParticle(Particle particle) {
        this.particle.add(particle);
    }
}
