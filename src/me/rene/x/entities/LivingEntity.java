package me.rene.x.entities;

import me.rene.x.utils.Vector2;

public abstract class LivingEntity extends Entity {

    private long health;
    private long maxHealth;
    private Vector2 direction;
    private boolean dead;

    public LivingEntity() {
        this.maxHealth = 0;
        this.health = 0;
        this.direction = Vector2.UP;
        this.dead = false;
    }

    public long getHealth() {
        return health;
    }

    public void setHealth(long health) {
        this.health = health;
    }

    public long getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(long maxHealth) {
        this.maxHealth = maxHealth;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
        if (dead) this.setVisible(false);
    }

    public <T extends Projectile>T launchProjectile(Class<T> projectile) {
        T instance = launchProjectile(projectile, this.direction);
        instance.setLauncher(this);
        //todo calculate damage
        instance.setDmg(1);
        return instance;
    }
}