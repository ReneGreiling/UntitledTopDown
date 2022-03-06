package me.rene.x.particle;

import me.rene.x.utils.Vector2;

public interface Particle {
    Vector2 getLocation();
    Vector2 getVelocity();
    float getRemainingTimeVisible();
    void lowerRemainingTimeVisibleBy(float time);
}