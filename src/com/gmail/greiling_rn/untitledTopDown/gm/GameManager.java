package com.gmail.greiling_rn.untitledTopDown.gm;

import com.gmail.greiling_rn.untitledTopDown.entities.Dummy;
import com.gmail.greiling_rn.untitledTopDown.entities.Entity;
import com.gmail.greiling_rn.untitledTopDown.entities.Player;
import com.gmail.greiling_rn.untitledTopDown.entities.Projectile;
import com.gmail.greiling_rn.untitledTopDown.events.EntityLaunchProjectileEvent;
import com.gmail.greiling_rn.untitledTopDown.events.EventManager;
import com.gmail.greiling_rn.untitledTopDown.events.PlayerMoveEvent;
import com.gmail.greiling_rn.untitledTopDown.io.Input;
import com.gmail.greiling_rn.untitledTopDown.particle.Particle;
import com.gmail.greiling_rn.untitledTopDown.particle.TextParticle;
import com.gmail.greiling_rn.untitledTopDown.utils.Camera;
import com.gmail.greiling_rn.untitledTopDown.utils.Vector2;

import java.awt.*;
import java.util.ArrayList;

public class GameManager implements Manager {
    private ArrayList<Entity> entitiesToRemove = new ArrayList<>();

    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Particle> particle = new ArrayList<>();
    
    private Player player = spawn(Player.class, new Vector2());
    private Camera camera = new Camera(new Vector2(), 1920./16);

    public GameManager() {
        spawn(Dummy.class, new Vector2(8 - .3, 2));
    }

    @Override
    public void draw(Graphics g) {
        entities.forEach(e -> {
            if (camera.isEntityShownByCamera(e) && e.isVisible()) {
                //todo draw entities
                if (e instanceof Dummy) {
                    g.setColor(Color.BLUE);
                    g.fillRect((int) camera.getLocationOnPanel(e.getLocation()).x,
                            (int) camera.getLocationOnPanel(e.getLocation()).y,
                            (int) camera.getLocationOnPanel(e.getSize()).x,
                            (int) camera.getLocationOnPanel(e.getSize()).y);
                } else if (e instanceof Projectile) {
                    g.setColor(Color.MAGENTA);
                    g.fillOval((int) camera.getLocationOnPanel(e.getLocation()).x,
                            (int) camera.getLocationOnPanel(e.getLocation()).y,
                            (int) camera.getLocationOnPanel(e.getSize()).x,
                            (int) camera.getLocationOnPanel(e.getSize()).y);
                }
            }
        });

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
                .add(camera.getLocationOnPanel(player.getDirection().divide(2)));

        g.drawLine((int) (playerLocOnPanel.x + playerSizeOnScreen.x / 2),
                ((int) (playerLocOnPanel.y + playerSizeOnScreen.y / 2)),
                (int) aimDirLinePoint2.x, (int) aimDirLinePoint2.y);

        Font font1 = new Font("Arial", Font.BOLD, (int) (camera.getSize() * .2));

        particle.forEach(p -> {
            if (p instanceof TextParticle) {
                TextParticle tp = (TextParticle) p;
                g.setColor(tp.getColor());
                g.setFont(font1);
                g.drawString(tp.getText(), (int) camera.getLocationOnPanel(tp.getLocation()).x -
                        (g.getFontMetrics().stringWidth(tp.getText()) / 2),
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

        player.setDirection(camera.getInWorldLocation(Input.lastMouseLocationOnPanel).subtract(player.getLocation()
                .add(player.getSize().divide(2))).normalise());

        //Remove expired particle
        ArrayList<Particle> toRemove = new ArrayList<>();
        particle.forEach(p -> {
            p.lowerRemainingTimeVisibleBy(dt);
            if (p.getRemainingTimeVisible() < 0) {
                toRemove.add(p);
            }
        });
        particle.removeAll(toRemove);
        toRemove.clear();

        particle.forEach(p -> p.setLocation(p.getLocation().add(p.getVelocity().multiply(10*dt))));

        entities.forEach(e -> e.update(dt));

        if (Input.isKeyDown(Input.InputType.ATTACK))
            EventManager.execEvent(new EntityLaunchProjectileEvent(player, player.launchProjectile(Projectile.class)));

        v = v.normalise().multiply(dt*10);

        if (!v.equals(new Vector2()))
            EventManager.execEvent(new PlayerMoveEvent(player, player.getLocation().add(v)));

        entities.removeAll(entitiesToRemove);
        entitiesToRemove.clear();
    }

    public void addParticle(Particle particle) {
        this.particle.add(particle);
    }

    public <T extends Entity>T spawn(Class<T> entity, Vector2 location) {
        try {
            T instance = entity.newInstance();
            instance.setLocation(location);
            entities.add(instance);
            return instance;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeEntity(Entity e) {
        entitiesToRemove.add(e);
    }
}