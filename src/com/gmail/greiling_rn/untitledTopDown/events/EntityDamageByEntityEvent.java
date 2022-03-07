package com.gmail.greiling_rn.untitledTopDown.events;

import com.gmail.greiling_rn.untitledTopDown.entities.Entity;
import com.gmail.greiling_rn.untitledTopDown.entities.LivingEntity;

public class EntityDamageByEntityEvent extends EntityDamageEvent {

    private Entity damageSource;

    public EntityDamageByEntityEvent(LivingEntity entity, int damage, Entity damageSource) {
        super(entity, damage);
        this.damageSource = damageSource;
    }

    public Entity getDamageSource() {
        return damageSource;
    }

    public void setDamageSource(Entity damageSource) {
        this.damageSource = damageSource;
    }
}
