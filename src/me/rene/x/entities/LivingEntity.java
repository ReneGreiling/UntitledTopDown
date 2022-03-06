package me.rene.x.entities;

import me.rene.x.utils.Vector2;

public abstract class LivingEntity extends Entity {

    private long health;
    private long maxHealth;

    public LivingEntity(Vector2 location, Vector2 size, long health) {
        super(location, size);
        this.maxHealth = health;
        this.health = health;
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
}