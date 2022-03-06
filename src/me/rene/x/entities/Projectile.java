package me.rene.x.entities;

import me.rene.x.utils.Vector2;

public class Projectile extends Entity {

    private Entity launcher;
    private int dmg;

    public Projectile(Vector2 location, Vector2 size) {
        super(location, size);
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
}
