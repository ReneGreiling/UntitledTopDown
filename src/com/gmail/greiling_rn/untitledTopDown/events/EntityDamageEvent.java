package com.gmail.greiling_rn.untitledTopDown.events;

import com.gmail.greiling_rn.untitledTopDown.entities.LivingEntity;

public class EntityDamageEvent extends Event {
    private LivingEntity entity;
    private int damage;

    public EntityDamageEvent(LivingEntity entity, int damage) {
        this.entity = entity;
        this.damage = damage;
    }

    public LivingEntity getEntity() {
        return entity;
    }

    public void setEntity(LivingEntity entity) {
        this.entity = entity;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}