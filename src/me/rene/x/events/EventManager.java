package me.rene.x.events;

import me.rene.x.Main;
import me.rene.x.gm.GameManager;
import me.rene.x.particle.TextParticle;
import me.rene.x.utils.Vector2;

import java.awt.*;

public class EventManager {
    //todo cleaner writing
    public static void execEvent(NoneCancelableEvent e) {
        if (e instanceof Event && ((Event) e).isCanceled())
            return;
        if (e instanceof EntityDamageByEntityEvent) {

            EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
            ((GameManager) Main.instance.manager).addParticle(new TextParticle(Integer.toString(event.getDamage()),
                    event.getEntity().getLocation().add(new Vector2(event.getEntity().getSize().x / 2, .1)),
                    new Color(255, 114, 40), new Vector2(0, -.1), .1F));

        } else if (e instanceof PlayerMoveEvent) {
            ((PlayerMoveEvent) e).getPlayer().setLocation(((PlayerMoveEvent) e).getTo());
        }
    }
}