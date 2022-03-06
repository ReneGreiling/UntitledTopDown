package me.rene.x.entities;

import me.rene.x.Main;
import me.rene.x.gm.GameManager;
import me.rene.x.utils.Vector2;

public abstract class Entity {

    private Vector2 location;
    private Vector2 size;
    private boolean visible;

    public Entity(){
        this.location = new Vector2();
        this.size = new Vector2();
        this.visible = true;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void update(float dt) {}

    public Vector2 getLocation() {
        return location;
    }

    public void setLocation(Vector2 location) {
        this.location = location;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public <T extends Projectile>T launchProjectile(Class<T> projectile, Vector2 velocity) {
        T instance = ((GameManager) Main.instance.manager).spawn(projectile,
                this.getLocation().add(this.getSize().divide(2)).add(velocity.normalise().divide(2)));
        instance.setVelocity(velocity);
        instance.setLocation(instance.getLocation().subtract(instance.getSize().divide(2)));
        instance.setLauncher(this);
        return instance;
    }
}