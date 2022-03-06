package me.rene.x.entities;

import me.rene.x.utils.Vector2;

public class Player extends LivingEntity {
    public Player(Vector2 location, long health) {
        super(location, new Vector2(.6, .6), health);
    }
}