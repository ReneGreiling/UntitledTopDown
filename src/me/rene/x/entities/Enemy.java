package me.rene.x.entities;

import me.rene.x.utils.Vector2;

public abstract class Enemy extends LivingEntity {
    public Enemy(Vector2 location, Vector2 size, long health) {
        super(location, size, health);
    }
}