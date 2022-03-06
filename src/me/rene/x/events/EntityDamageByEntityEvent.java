package me.rene.x.events;

import me.rene.x.entities.Entity;
import me.rene.x.entities.LivingEntity;

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
