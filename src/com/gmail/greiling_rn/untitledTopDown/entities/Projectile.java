package com.gmail.greiling_rn.untitledTopDown.entities;

import com.gmail.greiling_rn.untitledTopDown.gm.GameManager;
import com.gmail.greiling_rn.untitledTopDown.Main;
import com.gmail.greiling_rn.untitledTopDown.utils.Vector2;

public class Projectile extends Entity {

    private Entity launcher;
    private int dmg;
    private Vector2 velocity;
    private float lifetime;

    public Projectile() {
        this.setSize(new Vector2(.3, .3));
        lifetime = 2;
    }

    public Entity getLauncher() {
        return launcher;
    }

    public void setLauncher(Entity launcher) {
        this.launcher = launcher;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    @Override
    public void update(float dt) {
        lifetime -= dt;
        if (lifetime < 0)
            ((GameManager) Main.instance.manager).removeEntity(this);

        //todo collisions
        if (velocity != null)
            this.setLocation(getLocation().add(velocity.multiply(dt*10)));
    }
}
