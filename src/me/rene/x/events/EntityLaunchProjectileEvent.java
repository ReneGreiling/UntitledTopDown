package me.rene.x.events;

import me.rene.x.entities.Entity;
import me.rene.x.entities.Projectile;

public class EntityLaunchProjectileEvent extends Event {
    private Entity launcher;
    private Projectile projectile;

    public EntityLaunchProjectileEvent(Entity launcher, Projectile projectile) {
        this.launcher = launcher;
        this.projectile = projectile;
    }

    public Entity getLauncher() {
        return launcher;
    }

    public void setLauncher(Entity launcher) {
        this.launcher = launcher;
    }

    public Projectile getProjectile() {
        return projectile;
    }

    public void setProjectile(Projectile projectile) {
        this.projectile = projectile;
    }
}
